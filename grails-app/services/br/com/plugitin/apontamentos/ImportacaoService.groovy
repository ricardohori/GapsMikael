package br.com.plugitin.apontamentos

import java.text.SimpleDateFormat;

import br.com.plugitin.simpleexcelimport.SimpleExcelImport

class ImportacaoService {

    static transactional = true

	def static private CONFIG_FERIADO = [
		name:'feriados',
		startRow: 2,
		header:  [
			'A':'diaFeriado'
		],
		headerLine:[
			row: 1,
			names: ["Feriado"]
		],
		dateColumns:["diaFeriado"]
	]
	
	def static sdf = new SimpleDateFormat('dd MMMMM, yyyy', Locale.getDefault())
	
    def importarFeriados(xlsStream) {
		if(xlsStream){
			def workbook = SimpleExcelImport.excelImport(xlsStream, [CONFIG_FERIADO])
			gerarFeriados(workbook[CONFIG_FERIADO.name])
		}
    }
	
	def private gerarFeriados(feriadosSheet){
		feriadosSheet.each{conteudoLinhaFeriados->
			new Feriado(conteudoLinhaFeriados).save(flush:true, failOnError:true)
		}
	}
}
