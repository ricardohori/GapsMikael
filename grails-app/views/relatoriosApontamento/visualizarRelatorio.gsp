<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
    </head>
    
    <body>
    	<div id="totalHoras">
    		<span style="font-family: verdana; font-size: 14pt; margin-left: 20px">Total de horas trabalhadas: ${totalHorasPeriodo}</span>
    		<span style="font-family: verdana; font-size: 14pt; margin-left: 20px">Total de horas esperadas: ${horasEsperadasPeriodo}</span>
    		<span style="font-family: verdana; font-size: 14pt; margin-left: 20px">Saldo de horas: ${saldoHoras}</span>
    	</div>
    	<div id="relatorioDiv" style="display: block;">
            <div id="linechart"></div>
			<gvisualization:lineCoreChart dynamicLoading="${true}" elementId="linechart" title="Relatório de apontamentos mikael" 
			 width="${1400}" height="${400}" columns="${dadosGrafico.colunasRelatorio}" data="${dadosGrafico.dadosRelatorio}"/>
        </div>
        <div>
        	<table style="width:600px;">
        		<thead>
	        		<tr>
	        			<td style="font-weight: bold;">Apontamento<td>
	        			<td style="font-weight: bold;">Tipo<td>
	        			<td style="font-weight: bold;">Dia da Semana<td>
	        			<td style="font-weight: bold;">Duração<td>
	        		</tr>
	        	</thead>
	        	<g:each in="${dadosTabela}" var="value">
	        		<g:each in="${value.apontamentos}" var="apontamentoMap">
	        			<tr>
	        				<td>${apontamentoMap.apontamento}<td>
	        				<td>${apontamentoMap.tipo}<td>
	        				<td>${apontamentoMap.dSemana}<td>
	        				<td>${apontamentoMap.duracao}<td>
	        			</tr>		        			
	        		</g:each>
	        		<tr>
		        		<td style="font-weight: bold;">Total do Dia:</td>
		        		<td style="font-weight: bold;">${value.totalDia}</td>
        			</tr>
	        	</g:each>
        	</table>
        </div>
    </body>
</html>
