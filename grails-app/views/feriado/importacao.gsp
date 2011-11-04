<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
    </head>
    
    <body>	
    	<g:uploadForm id="importacaoForm" name="importacaoForm" action="importar" method="post" enctype="multipart/form-data">
    		<div id="formDiv" style="padding: 15px" >
		    	<div id="planilhaFeriadosDiv" style="display: block;">
		            <label for="planilhaFeriados" style="">Planilha feriados: </label>
		            <input id="planilhaFeriados" name="planilhaFeriados" type="file" />
		        </div>
		        <div id="submitDiv" style="display: block;">
		            <g:submitButton name="importarPlanilha" value="Importar"></g:submitButton>
		        </div>
	        </div>
	    </g:uploadForm>
    </body>
</html>
