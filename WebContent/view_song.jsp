
<%@ page import="model.*" %>
<%@ page import="command.*" %>
<%
GetSongCommand cmd = new GetSongCommand();
Song s = cmd.execute(Integer.parseInt(request.getParameter("id")));
out.print("<br/>Title: "+s.getTitle());
out.print("<br/>Artist: "+s.getArtist());
%>