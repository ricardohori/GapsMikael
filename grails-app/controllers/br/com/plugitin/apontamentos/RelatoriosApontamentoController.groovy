package br.com.plugitin.apontamentos

import javax.swing.text.View;

class RelatoriosApontamentoController {
	
	def geracaoRelatorioApontamentosService

    def index = { 
			
	}
	
	def gerarRelatorio = {
		def dados = geracaoRelatorioApontamentosService.gerarRelatorioApontamentos(params.sigla,params.inicioPeriodo,params.fimPeriodo)
		render(view:"visualizarRelatorio", model:dados)
	}
}
