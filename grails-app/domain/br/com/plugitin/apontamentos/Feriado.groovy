package br.com.plugitin.apontamentos

import java.text.SimpleDateFormat;

class Feriado {

	Date diaFeriado
	String diaFeriadoString
	
	def beforeInsert = {
		def sdf = new SimpleDateFormat('dd/MM/yyyy')
		diaFeriadoString = sdf.format(diaFeriado)
	}
	
    static constraints = {
		diaFeriadoString nullable:true,blank:true
    }
}
