<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
    </head>
    
    <body>
        <div>
        	<table style="width:600px;">
        		<thead>
	        		<tr>
	        			<td style="font-weight: bold;">Titulo<td>
	        			<td style="font-weight: bold;">Estoria<td>
	        			<td style="font-weight: bold;">Sprint<td>
	        			<td style="font-weight: bold;">Tipo<td>
	        			<td style="font-weight: bold;">Funcionalidade<td>
	        			<td style="font-weight: bold;">Responsavel<td>
	        			<td style="font-weight: bold;">Severidade<td>
	        			<td style="font-weight: bold;">Descrição<td>
	        			<td style="font-weight: bold;">Ambiente<td>
	        			<td style="font-weight: bold;">Data Ocorrência<td>
	        			<td style="font-weight: bold;">Nº do Chamado<td>
	        			<td style="font-weight: bold;">Status<td>
	        			<td style="font-weight: bold;">Data Conclusão<td>
	        		</tr>
	        	</thead>
	        	<g:each in="${dadosTabela}" var="itensMap">
        			<tr>
        				<td >${itensMap.TITULO}<td>
        				<td >${itensMap.ESTORIA}<td>
        				<td >${itensMap.SPRINT}<td>
        				<td >${itensMap.TIPO}<td>
       					<td >${itensMap.FUNCIONALIDADE}<td>
        				<td >${itensMap.RESPONSAVEL}<td>
        				<td >${itensMap.SEVERIDADE}<td>
        				<td >${itensMap.BREVE_DESCRICAO}<td>
        				<td >${itensMap.AMBIENTE}<td>
        				<td >${itensMap.DATA_OCORRENCIA}<td>
        				<td >${itensMap.N_CHAMADO}<td>
        				<td >${itensMap.STATUS}<td>
        				<td >${itensMap.DATA_CONCLUSAO}<td>
        			</tr>		        			
	        	</g:each>
        	</table>
        </div>
    </body>
</html>