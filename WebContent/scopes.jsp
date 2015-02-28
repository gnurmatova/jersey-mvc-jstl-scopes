<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
 <%@ page import="model.*" %>
 <%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Passing Model to MVC example</title>
</head>
<body>
  <h1>you are logged in as ${it.user}!</h1>
  <p>
    <c:out value="request scope variable:${requestScope.rVar}"></c:out><br/>
    <c:out value="session scope variable:${sessionScope.sVar}"></c:out><br/>
    <c:out value="application scope variable:${applicationScope.aVar}"></c:out><br/>
    
  </p>
</body>
</html>