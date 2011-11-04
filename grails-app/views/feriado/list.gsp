
<%@ page import="br.com.plugitin.apontamentos.Feriado" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'feriado.label', default: 'Feriado')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'feriado.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="diaFeriado" title="${message(code: 'feriado.diaFeriado.label', default: 'Dia Feriado')}" />
                            
                             <g:sortableColumn property="diaFeriadoString" title="${message(code: 'feriado.diaFeriadoString.label', default: 'Dia Feriado String')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${feriadoInstanceList}" status="i" var="feriadoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${feriadoInstance.id}">${fieldValue(bean: feriadoInstance, field: "id")}</g:link></td>
                        
                            <td><g:formatDate date="${feriadoInstance.diaFeriado}" /></td>
                            
                            <td>${fieldValue(bean: feriadoInstance, field: "diaFeriadoString")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${feriadoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
