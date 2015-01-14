<%@ page import="com.luckypants.model.Book"%>
Lucky Pants Books
<br/>
<%
Book book1 = new Book();
book1.setTitle("My Web Book");
book1. setAuthor("Web Book Author");
%>
<br/>
Book1:
<br/>
Title:<%=book1.getTitle()%><br/>
Author:<%=book1.getAuthor()%>