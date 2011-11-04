package br.com.plugitin.apontamentos

import groovy.sql.Sql

import java.text.SimpleDateFormat

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet

class GerarRelatorioApontamentos {
	def static sdf = new SimpleDateFormat('dd/MM/yy kk:mm:ss')
	def static sdfDay = new SimpleDateFormat('dd')

	static main(args) {
		def db = [url:'jdbc:oracle:thin:@SPDEVDB04:1521:DES', user:'sgi_ro', password:'SGI_RO', driver:'oracle.jdbc.driver.OracleDriver']
		Sql sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
		
		def queryBuilder = new StringBuilder()
		
		queryBuilder.append "SELECT DURACAO, to_char(DH_APONTAMENTO, 'dd/mm/yy hh24:mi:ss') as DH_APONTAMENTO, to_char(DH_TERMINO, 'dd/mm/yy hh24:mi:ss') as DH_TERMINO, TIPO_APONTAMENTO as TIPO_APONTAMENTO FROM MKL_APONTAMENTO "
		queryBuilder.append "WHERE USU_ID = (SELECT ID FROM MKL_USUARIO WHERE SIGLA = 'RFH') "
		queryBuilder.append "AND DH_APONTAMENTO BETWEEN TO_DATE('26/10/11', 'DD/MM/YY') AND TO_DATE('26/11/11', 'DD/MM/YY') "
		queryBuilder.append "ORDER BY TO_DATE(DH_APONTAMENTO, 'dd/mm/yy hh24:mi:ss') ASC"
		
		def rowsPorDia = sql.rows(queryBuilder.toString()).groupBy{sdfDay.format(sdf.parse(it.DH_APONTAMENTO))}
		gerarRelatorio(rowsPorDia)
	}
	
	def static gerarRelatorio(rowsPorDia){
		def workbook = new HSSFWorkbook()
		
		def file = new File("D:\\relatorioApontamentos.xls")
		file.createNewFile()
		def outFile = file.newOutputStream()

		Sheet apontamentosSheet = workbook.createSheet("Apontamentos")
		
		Row rowHeader = apontamentosSheet.createRow(0)
		
		Cell cellHeader0 = rowHeader.createCell(0)
		cellHeader0.setCellValue("Dia")
		
		Cell cellHeader1 = rowHeader.createCell(1)
		cellHeader1.setCellValue("Hora")
		
		Cell cellHeader2 = rowHeader.createCell(2)
		cellHeader2.setCellValue("Tipo")
		
		def i = 1
		def totalMes = []
		
		def timeFormatter = new SimpleDateFormat('kk:mm:ss')
		
		rowsPorDia.each{dia, row->
			row.each{subRow->
				Row linha = apontamentosSheet.createRow(i+1)
				linha.createCell(0).setCellValue(dia)
				linha.createCell(1).setCellValue(timeFormatter.format(sdf.parse(subRow.DH_APONTAMENTO)))
				linha.createCell(2).setCellValue(subRow.TIPO_APONTAMENTO)
				i++
			}
			Row linha = apontamentosSheet.createRow(i+1)
			linha.createCell(0).setCellValue("Total do Dia:")
			def trabalhoDia = row.findAll{it.DURACAO}.sum{it.DURACAO} as Double
			totalMes << trabalhoDia
			def horas = trabalhoDia/60 as Integer
			def minutos = trabalhoDia%60 as Integer
			linha.createCell(1).setCellValue("$horas:$minutos")
			i++
		}
		
		Row linha = apontamentosSheet.createRow(i+1)
		linha.createCell(0).setCellValue("Total do período:")
		linha.createCell(1).setCellValue("${totalMes.sum()/60 as Integer}:${totalMes.sum()%60 as Integer}")
		
		Row linha2 = apontamentosSheet.createRow(i+2)
		linha2.createCell(0).setCellValue("Média do período:")
		linha2.createCell(1).setCellValue("${(totalMes.sum()/60)/totalMes.size() as Integer}:${(totalMes.sum()%60)/totalMes.size() as Integer}")
		linha2.createCell(2).setCellValue("Quantidade de dias: ${totalMes.size()}")
		
		apontamentosSheet.autoSizeColumn((short) 0)
		apontamentosSheet.autoSizeColumn((short) 1)
		apontamentosSheet.autoSizeColumn((short) 2)
		
		workbook.write(outFile)
		outFile.flush()
		outFile.close()
	}
}
