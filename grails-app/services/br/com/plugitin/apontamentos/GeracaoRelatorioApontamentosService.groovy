package br.com.plugitin.apontamentos

import groovy.sql.Sql

import java.text.DecimalFormat
import java.text.SimpleDateFormat

import org.apache.poi.hssf.record.formula.functions.T

import br.com.plugitin.apontamentos.Feriado

class GeracaoRelatorioApontamentosService {

    static transactional = true
	
	def static sdf = new SimpleDateFormat('dd/MM/yy kk:mm:ss')
	def static sdfDay = new SimpleDateFormat('dd/MM/yyyy')
	def static cal = GregorianCalendar.getInstance()
	static DecimalFormat numFormat = DecimalFormat.getInstance()
	
	def private static formatarHoras = {qtdHoras ->
		numFormat.parse(numFormat.format(qtdHoras))
	}

    def gerarRelatorioApontamentos(sigla,inicioPeriodo,fimPeriodo) {
		numFormat.maximumFractionDigits = 2 as int
		def db = [url:'jdbc:oracle:thin:@SPDEVDB04:1521:DES', user:'sgi_ro', password:'SGI_RO', driver:'oracle.jdbc.driver.OracleDriver']
		Sql sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
		
		def queryBuilder = new StringBuilder()
		
		queryBuilder.append "SELECT DURACAO, to_char(DH_APONTAMENTO, 'dd/mm/yy hh24:mi:ss') as DH_APONTAMENTO, to_char(DH_TERMINO, 'dd/mm/yy hh24:mi:ss') as DH_TERMINO, TIPO_APONTAMENTO as TIPO_APONTAMENTO FROM MKL_APONTAMENTO "
		queryBuilder.append "WHERE USU_ID = (SELECT ID FROM MKL_USUARIO WHERE SIGLA = '$sigla') "
		queryBuilder.append "AND DH_APONTAMENTO BETWEEN TO_DATE('$inicioPeriodo', 'DD/MM/YY') AND TO_DATE('$fimPeriodo', 'DD/MM/YY') "
		queryBuilder.append "ORDER BY TO_DATE(DH_APONTAMENTO, 'dd/mm/yy hh24:mi:ss') ASC"
		
		def rowsPorDia = sql.rows(queryBuilder.toString()).groupBy{sdfDay.format(sdf.parse(it.DH_APONTAMENTO))}
		def totalPeriodo = []
		def dadosRelatorio = []
		
		rowsPorDia.each{dia, row->
			def trabalhoDia = row.findAll{it.DURACAO}.sum{it.DURACAO} as Double
			totalPeriodo << trabalhoDia
			dadosRelatorio << ["$dia",formatarHoras(trabalhoDia/60)]
		}
		def totalHorasPeriodo = totalPeriodo.sum()/60
		def feriados = Feriado.list().collect{it.diaFeriadoString}
		
		def diaAnalisado = sdfDay.parse(inicioPeriodo)
		def diaFinalPeriodo = sdfDay.parse(fimPeriodo)
		def diasUteis = 0
		
		while(diaAnalisado < diaFinalPeriodo) {
			def bool = !feriados.contains(sdfDay.format(diaAnalisado))
			cal.setTime diaAnalisado
			bool = bool && !(cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.DAY_OF_WEEK) == 7)
			
			if(bool) diasUteis++
			diaAnalisado = diaAnalisado + 1
		}
		
		def media = totalHorasPeriodo/totalPeriodo.size()
		
		dadosRelatorio.each{dado->
			dado << formatarHoras(media)
		}
		
		def colunasRelatorio = [['string', 'Dia'], ['number', 'Horas Trabalhadas'], ['number','Média']]
		def horasEsperadasPeriodo = diasUteis*8 as Double
		totalHorasPeriodo = formatarHoras(totalHorasPeriodo) as Double
		def saldoHoras = totalHorasPeriodo - horasEsperadasPeriodo 
		
		def dadosGrafico = [colunasRelatorio:colunasRelatorio, dadosRelatorio:dadosRelatorio]
		def dadosTabela = []
		
		def i = 1
		def timeFormatter = new SimpleDateFormat('kk:mm:ss')
		rowsPorDia.each{dia, row->
			def linha = [apontamentos:[]]
			row.each{subRow->
				cal.setTime sdf.parse(subRow.DH_APONTAMENTO)
				def horas = (subRow.DURACAO as Double)/60 as Integer
				def minutos = (subRow.DURACAO as Double)%60 as Integer
				linha.apontamentos << [apontamento:timeFormatter.format(sdf.parse(subRow.DH_APONTAMENTO)), tipo:subRow.TIPO_APONTAMENTO,
					dSemana:"${day(cal.get(Calendar.DAY_OF_WEEK))} - ${dia}", duracao:"$horas:$minutos"]
				i++
			}
			def trabalhoDia = row.findAll{it.DURACAO}.sum{it.DURACAO} as Double
			def horas = trabalhoDia/60 as Integer
			def minutos = trabalhoDia%60 as Integer
			linha["totalDia"] = "$horas:$minutos"
			dadosTabela << linha
			i++
		}
		
		return [dadosGrafico:dadosGrafico, dadosTabela:dadosTabela, totalHorasPeriodo:totalHorasPeriodo, horasEsperadasPeriodo:horasEsperadasPeriodo, saldoHoras:saldoHoras]
    }
	
	def static private day(day){
		switch(day){
			case 1:
				return "Domingo"
			case 2:
				return "Segunda-Feira"
			case 3:
				return "Terça-Feira"
			case 4:
				return "Quarta-Feira"
			case 5:
				return "Quinta-Feira"
			case 6:
				return "Sexta-Feira"
			case 7:
				return "Sábado"
			default:
				break
		}
	}
}
