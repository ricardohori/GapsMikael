package br.com.plugitin.apontamentos

import groovy.sql.Sql

import java.text.DecimalFormat
import java.text.SimpleDateFormat

import org.apache.poi.hssf.record.formula.functions.T

import br.com.plugitin.apontamentos.Feriado

class GeracaoRelatorioBugsMelhoriasService {

    static transactional = true
	
	def static sdf = new SimpleDateFormat('dd/MM/yy kk:mm:ss')
	def static sdfDay = new SimpleDateFormat('dd/MM/yyyy')
	def static cal = GregorianCalendar.getInstance()
	static DecimalFormat numFormat = DecimalFormat.getInstance()
	
    def gerarRelatorioBugsMelhorias(bugs=true, melhorias=true, encerrados=true) {
		def db = [url:'jdbc:oracle:thin:@SPDEVDB04:1521:DES', user:'sgi_ro', password:'SGI_RO', driver:'oracle.jdbc.driver.OracleDriver']
		Sql sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
		
		def queryBuilder = new StringBuilder()
		
		queryBuilder.append "SELECT TITULO, ESTORIA, SPRINT, TIPO, FUNCIONALIDADE, RESPONSAVEL,	SEVERIDADE,	BREVE_DESCRICAO, AMBIENTE, DATA_OCORRENCIA, N_CHAMADO,	STATUS,	DATA_CONCLUSAO FROM ITAX_BUGS_MELHORIAS "
		if(bugs || melhorias || !encerrados) queryBuilder.append "WHERE "
		if(melhorias && bugs) queryBuilder.append "tipo in ('BUG','MELHORIA') "
		if(!melhorias && bugs) queryBuilder.append "tipo = 'BUG' "
		if(melhorias && !bugs) queryBuilder.append "tipo = 'MELHORIA' "
		if(!encerrados)queryBuilder.append "AND DATA_CONCLUSAO IS NULL "
		
		def rows = sql.rows(queryBuilder.toString())
		return [dadosTabela:rows]
    }
}
