<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <link rel="stylesheet" type=text/css href="${resource(dir: 'css', file: 'relatorio-bugs.css')}" />
    </head>
    
    <body>
        <g:if test="${flash.message}">
            <div class="errors">${flash.message}</div>
        </g:if>
        <div>
        	<g:form controller="relatorioBugsMelhorias" action="gerarRelatorio">
        		<fieldset id="frameFiltro">
        			<legend>Filtrar por:</legend>
		        	<div id="painelFiltro">
			        	<g:checkBox name="melhorias" value="${melhorias}"/><span >Exibir melhorias?</span>
			        	<g:checkBox name="bugs" value="${bugs}"/><span>Exibir BUGs?</span>
			        	<g:checkBox name="encerradas" value="${encerradas}"/><span>Exibir Encerradas?</span>
			        	<g:submitButton name="pesquisar" value="Aplicar filtro"/>
		        	</div>
	        	</fieldset>
	
	        	<table class="relatorio-bugs">
	        		<thead>
		        		<tr>
		        			<td style="font-weight: bold;"></td>
		        			<g:sortableColumn property="TITULO" title="Titulo" params="[melhorias:melhorias, bugs:bugs, encerradas:encerradas]"/>
		        			<g:sortableColumn property="ESTORIA" title="Estoria" params="[melhorias:melhorias, bugs:bugs, encerradas:encerradas]"/>
		        			<g:sortableColumn property="SPRINT" title="Sprint" params="[melhorias:melhorias, bugs:bugs, encerradas:encerradas]"/>
		        			<g:sortableColumn property="TIPO" title="Tipo" params="[melhorias:melhorias, bugs:bugs, encerradas:encerradas]"/>
		        			<g:sortableColumn property="FUNCIONALIDADE" title="Funcionalidade" params="[melhorias:melhorias, bugs:bugs, encerradas:encerradas]"/>
		        			<g:sortableColumn property="RESPONSAVEL" title="Responsavel" params="[melhorias:melhorias, bugs:bugs, encerradas:encerradas]"/>
		        			<g:sortableColumn property="SEVERIDADE" title="Severidade" params="[melhorias:melhorias, bugs:bugs, encerradas:encerradas]"/>
		        			<g:sortableColumn property="BREVE_DESCRICAO" title="Descrição" params="[melhorias:melhorias, bugs:bugs, encerradas:encerradas]"/>
		        			<g:sortableColumn property="AMBIENTE" title="Ambiente" params="[melhorias:melhorias, bugs:bugs, encerradas:encerradas]"/>
		        			<g:sortableColumn property="DATA_OCORRENCIA" title="Data Ocorrência" params="[melhorias:melhorias, bugs:bugs, encerradas:encerradas]"/>
		        			<g:sortableColumn property="N_CHAMADO" title="Nº do Chamado" params="[melhorias:melhorias, bugs:bugs, encerradas:encerradas]"/>
		        			<g:sortableColumn property="STATUS" title="Status" params="[melhorias:melhorias, bugs:bugs, encerradas:encerradas]"/>
		        			<g:if test="${encerradas}">
		        				<g:sortableColumn property="DATA_CONCLUSAO" title="Data Conclusão" params="[melhorias:melhorias, bugs:bugs, encerradas:encerradas]"/>
		        			</g:if>
		        			<td style="font-weight: bold;"></td>
		        		</tr>
		        	</thead>
		        	<g:each in="${dadosTabela}" var="itensMap" status="linha">
	        			<tr >
							<td style="background-color: rgb(242,242,242);">${linha + 1}</td>
	        				<td >${itensMap.TITULO}</td>
	        				<td >${itensMap.ESTORIA}</td>
	        				<td >${itensMap.SPRINT}</td>
	        				<td class="${itensMap.TIPO == 'BUG'?"tipo-bug":"tipo-melhoria"}">${itensMap.TIPO}</td>
	       					<td >${itensMap.FUNCIONALIDADE}</td>
	        				<td >${itensMap.RESPONSAVEL}</td>
	        				<td class="severidade-${itensMap.SEVERIDADE}">${itensMap.SEVERIDADE}</td>
	        				<td >${itensMap.BREVE_DESCRICAO}</td>
	        				<td >${itensMap.AMBIENTE}</td>
	        				<td ><g:formatDate date="${itensMap.DATA_OCORRENCIA}" format="dd/MM/yyyy"/></td>
	        				<td >${itensMap.N_CHAMADO}</td>
	        				<td >${itensMap.STATUS}</td>
	        				<g:if test="${encerradas}">
	        					<td ><g:formatDate date="${itensMap.DATA_CONCLUSAO}" format="dd/MM/yyyy"/></td>
	        				</g:if>
	        				<td style="background-color: rgb(242,242,242);"></td>
	        			</tr>		        			
		        	</g:each>
	        	</table>
       		</g:form>
        </div>
    </body>
</html>
