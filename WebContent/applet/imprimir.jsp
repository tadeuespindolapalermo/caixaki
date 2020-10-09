<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Imprimindo...</title>
	<script type="text/javascript" src="<%= request.getContextPath()%>/javax.faces.resource/jquery.js.jsf?ln=primefaces/jquery"></script>
	<script type="text/javascript" src="../resources/javascript/scripty.js"></script>
	<script type="text/javascript" src="../resources/javascript/deployJava.js"></script>
</head>
<body style="margin-bottom: 1;margin-top: 1;margin-left: 1;margin-right: 1">
	<h6 style="margin-bottom: 1;margin-top: 1;">Imprimindo...</h6>
	<jsp:plugin
		code="JalisAppletImpressao.class"
		align="top"
		codebase="../applet"
	 	type="applet" 
		name="jalisApplet"   
		jreversion="1.6"
	 	archive="jalisApplet.jar" 
	 	height="100" width="100" >  
	 	
	 	<jsp:params>
	 		<jsp:param name="permissions" value="all-permissions"/> 
	 		<jsp:param name="impressoraImprimir" value="<%=request.getParameter(\"impressoraImprimir\")%>"/>  
		</jsp:params> 
		<jsp:fallback>
			<p>Não foi possivel carregar applet</p>
		</jsp:fallback>
	</jsp:plugin>
	
	<script type="text/javascript">
		function fecharImpessao(terminou) {
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