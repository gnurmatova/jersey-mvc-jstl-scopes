
<%@ page import="model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="song" value="${it.song}" scope="page"></c:set>

<br/>Title: ${song.getTitle()}
<br/>Artist: ${song.getArtist()}