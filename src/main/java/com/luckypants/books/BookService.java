package com.luckypants.books;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.luckypants.model.Book;

@Path("/books")
public class BookService {

	ArrayList books = new ArrayList();
	Book book1 = new Book();

	@GET
	@Path("/")
	public Response getBook() {
		String response = books.toString();
		return Response.status(200).entity(response).build();
	}

	@GET
	@Path("/{username}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getName(@PathParam("username") String userName) {
		String response = "Hello " + userName;
		return Response.status(200).entity(response).build();
	}

	@POST
	@Path("/")
	@Consumes("application/x-www-form-urlencoded")
	public Response setBook(@FormParam("title") String title,
			@FormParam("author") String author) {
		book1.setTitle(title);
		book1.setAuthor(author);
		books.add(book1);
		String response = "Title:" + book1.getTitle() + " " + "Author:"
				+ book1.getAuthor();
		return Response.status(201).entity(response).build();
	}
}
