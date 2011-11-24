package br.com.plugitin.apontamentos

import javax.swing.text.View;

class RelatorioBugsMelhoriasController {
	
	def geracaoRelatorioBugsMelhoriasService

    def index = { 
			params.melhorias=true
			params.bugs=true
			redirect(action:"gerarRelatorio", params:params)
	}
	
	def gerarRelatorio = {
		if(!params.melhorias && !params.bugs){
			flash.message = "Você selecionar ao menos uma opção entre BUGS e Melhorias."
			render(view:"visualizarBugsMelhorias", model:[dadosTabela:[:], melhorias:params.melhorias, bugs:params.bugs, encerradas:params.encerradas])
		}else{
			def dados = geracaoRelatorioBugsMelhoriasService.gerarRelatorioBugsMelhorias(params.bugs, params.melhorias, params.encerradas, params['sort'], params.order)
			render(view:"visualizarBugsMelhorias", model:[dadosTabela:dados.dadosTabela, melhorias:params.melhorias, bugs:params.bugs, encerradas:params.encerradas])
		}
	}
}
