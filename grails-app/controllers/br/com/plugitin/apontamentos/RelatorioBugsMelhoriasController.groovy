package br.com.plugitin.apontamentos

import javax.swing.text.View;

class RelatorioBugsMelhoriasController {
	
	def geracaoRelatorioBugsMelhoriasService

    def index = { 
			redirect(action:"gerarRelatorio")
	}
	
	def gerarRelatorio = {
		def dados = geracaoRelatorioBugsMelhoriasService.gerarRelatorioBugsMelhorias()
		render(view:"visualizarBugsMelhorias", model:dados)
	}
}
