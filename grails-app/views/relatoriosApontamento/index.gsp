<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
    </head>
    
    <body>	
    	<g:form id="relatorioForm" name="relatorioForm" action="gerarRelatorio">
    		<div id="formDiv" style="padding: 15px">
		    	<div id="siglaDiv" style="display: block;">
		            <label for="sigla" style="">Sigla: </label>
		            <input id="sigla" maxlength="3" name="sigla" type="text" />
		        </div>
		    	<div id="inicioPeriodoDiv" style="display: inline;">
		            <label for="inicioPeriodo">Início do Período: </label>
		            <input id="inicioPeriodo" maxlength="10" name="inicioPeriodo" type="text" class="calendario"/>
		        </div>
		        <div id="fimPeriodoDiv" style="display: inline;">
		            <label for="fimPeriodo">Fim do Período: </label>
		            <input id="fimPeriodo" maxlength="10" name="fimPeriodo" type="text" class="calendario2"/>
		        </div>
		        <div id="submitDiv" style="display: block;">
		            <g:submitButton name="gerarRelatorio" value="Gerar Relatório"></g:submitButton>
		        </div>
	        </div>
	    </g:form>
    </body>
</html>
