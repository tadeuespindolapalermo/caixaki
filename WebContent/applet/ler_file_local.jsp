<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="<%= request.getContextPath()%>/javax.faces.resource/jquery.js.jsf?ln=primefaces/jquery"></script>
	<script type="text/javascript" src="../resources/javascript/scripty.js"></script>
	<script type="text/javascript" src="../resources/javascript/deployJava.js"></script>
</head>

<body style="margin-bottom: 1;margin-top: 1;margin-left: 1;margin-right: 1">
	<h6 style="margin-bottom: 1;margin-top: 1;">Lendo arquivo local...</h6>
	<%
		String contextNameApp = request.getContextPath();
		request.setAttribute("contextNameApp",contextNameApp);
	%>
	  	<jsp:plugin codebase="../applet" 
	  	    align="top"
			code="JalisApplet.class"
		 	type="applet" 
			name="jalisApplet" 
		 	archive="jalisApplet.jar" 
		 	jreversion="1.6"
		 	height="200" width="200" 
		 	nspluginurl="http://java.sun.com/j2se/1.4.2/download.html"
            iepluginurl="http://java.sun.com/j2se/1.4.2/download.html">
		 	
		 	<jsp:params>
		 		<jsp:param value="all-permissions" name="permissions"/>
				<jsp:param value="valor do parametro" name="parametroApplet"/>
			</jsp:params>
			<jsp:fallback>
				<p>Não foi possivel carregar applet</p>
			</jsp:fallback>
		</jsp:plugin>
		
	    <!-- Aqui ele é invocado pela segunda vez por java script direto -->
	    <!--<script>
			executeApplet("${contextNameApp}");
		</script>
		-->
		<div>
			<input type="hidden"  id="respostaPropertiesElement" />
		</div>
		
		<script type="text/javascript">
		function fecharLeitura(terminou) {
			if (terminou){
				window.close();
			}else {
				setTimeout(function() { 
					window.close(); 
				}, 11000);
			}
		}
	</script>
</body>
</html>