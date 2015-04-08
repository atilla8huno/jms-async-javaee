<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="jmsController" accept-charset="ISO-8859-1">
		Mensagem: <br/>
		<input type="text" name="mensagem" id="mensagem" />
		<input type="submit" value="Enviar Msg" /><br/><br/>
		
		<a href="http://localhost:8161/admin/queues.jsp">Ver JMS Server</a>
	</form>
</body>
</html>