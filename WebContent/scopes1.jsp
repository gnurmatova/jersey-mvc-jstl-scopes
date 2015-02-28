<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Passing Model to MVC example</title>
</head>
<body>

	<form action="scopes2.jsp" method="GET">
		This is the starting page, it sets the values of 3 scope variables,
		displayed below:<br /> <br /> <br />
		<c:set var="pVar" value="0" scope="page"></c:set>
		<c:set var="rVar" value="100" scope="request"></c:set>
		<c:set var="sVar" value="200" scope="session"></c:set>
		<c:set var="aVar" value="300" scope="application"></c:set>


		<c:out value="page scope variable:${pageScope.pVar}"></c:out>
		<br />
		<c:out value="request scope variable:${requestScope.rVar}"></c:out>
		<br />
		<c:out value="session scope variable:${sessionScope.sVar}"></c:out>
		<br />
		<c:out value="application scope variable:${applicationScope.aVar}"></c:out>
		<br /> <br /> <br />

		<input type="submit"> <a href="scopesr.jsp">Click here to
			experience the redirect!</a>
	</form>

</body>
</html>