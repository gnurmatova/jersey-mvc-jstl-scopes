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
<form>
<c:set var="pVar" value="1" scope="page"></c:set>
     <c:set var="rVar" value="101" scope="request"></c:set>
    <c:set var="sVar" value="201" scope="session"></c:set>
    <c:set var="aVar" value="301" scope="application"></c:set>
</form>    
 <%request.getRequestDispatcher("scopes2.jsp").forward(request,response);%>
</body>
</html>