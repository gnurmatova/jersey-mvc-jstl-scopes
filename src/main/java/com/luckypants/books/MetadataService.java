package com.luckypants.books;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luckypants.model.Book;

@Path("/metadata")
public class MetadataService {
	ObjectMapper mapper = new ObjectMapper();

	@GET
	@Path("/book")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBookMetadata() {
		Book book = new Book();
		String bookJSON;
		try {
			bookJSON = mapper.writeValueAsString(book);
			return Response.status(200).entity(bookJSON).build();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}

}
