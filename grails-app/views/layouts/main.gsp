<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <link rel="stylesheet" type=text/css href="${resource(dir: 'css', file: 'jquery-ui-1.8.13.custom.css')}" />
        <g:layoutHead />
        <g:javascript library="application" />
        <script src="${resource(dir: 'js/jquery', file: 'jquery-1.4.4.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js/jquery', file: 'jquery-ui-1.8.13.custom.min.js')}" type="text/javascript"></script>
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>
        <g:javascript library="calendario" />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div>
        <g:layoutBody />
    </body>
</html>