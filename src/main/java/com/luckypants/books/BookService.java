package com.luckypants.books;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luckypants.command.CreateBookCommand;
import com.luckypants.command.DeleteBookCommand;
import com.luckypants.command.GetBookCommand;
import com.luckypants.command.ListAllBooksCommand;
import com.luckypants.model.Book;

@Path("/books")
public class BookService {
	ObjectMapper mapper = new ObjectMapper();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listBooks() {
		ListAllBooksCommand listBooks = new ListAllBooksCommand();
		ArrayList<Book> list = listBooks.execute();
		String booksString = null;
		try {
			booksString = mapper.writeValueAsString(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(booksString).build();
	}


	@GET
	@Path("/{key}/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("key") String key,
			@PathParam("value") String value) {
		GetBookCommand getBookCommand = new GetBookCommand();
		Book book = getBookCommand.execute(key, value);
		String bookString = null;
		try {
			bookString = mapper.writeValueAsString(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(bookString).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response createBook(String bookStr) {

		try {
			CreateBookCommand create = new CreateBookCommand();
			Book book = mapper.readValue(bookStr, Book.class);
			boolean success = create.execute(book);
			String bookJSON = mapper.writeValueAsString(book);
			if (success) {
				return Response.status(201).entity(bookJSON).build();
			} else
				return Response.status(500).entity("").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.toString()).build();
		}
	}

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchBook(
			@DefaultValue("*") @QueryParam("query") String query,
			@DefaultValue("author") @QueryParam("sortby") String sortby) {
		HashMap<String, String> responseMap = new HashMap<String, String>();
		responseMap.put("query", query);
		responseMap.put("sortby", sortby);
		String rString = "";
		try {
			rString = mapper.writeValueAsString(responseMap);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(e.toString()).build();
		}
		return Response.status(200).entity(rString).build();
	}

	@DELETE
	@Path("/{isbn}")
	public Response deleteBook(@PathParam("isbn") String isbn) {
		DeleteBookCommand delete = new DeleteBookCommand();
		delete.execute(isbn);
		return Response.status(200).build();
	}
	
	@GET
	@Path("twitter")
	@Produces(MediaType.WILDCARD)
	public Response textTwitter() {
		
		return Response.status(200).entity("").build();
	}
}
