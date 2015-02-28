<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
 <%@ page import="model.*" %>
 <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Passing Model to MVC example</title>
</head>
<body>
  <h1>you are logged in as ${it.user}!</h1>
  <p>
    songs available in the store :<br />
    <% HashMap<String, Object> it = (HashMap<String, Object>)pageContext.findAttribute("it");
       for(Song song : (ArrayList<Song>)it.get("songs")){
    	   out.println("<br><b>"+song.getTitle()+"</b> by <i>"+song.getArtist()+"</i>");
       }
    %>
  </p>
</body>
</html>