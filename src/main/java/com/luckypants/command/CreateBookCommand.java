package com.luckypants.command;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luckypants.model.Author;
import com.luckypants.model.Book;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class CreateBookCommand {

	public boolean execute(Book book) {
		ConnectionProvider booksConn = new ConnectionProvider();
		DBCollection booksCollection = booksConn.getCollection("books");

		ObjectMapper mapper = new ObjectMapper();
		try {
			DBObject dbObject = (DBObject) JSON.parse(mapper
					.writeValueAsString(book));
			booksCollection.insert(dbObject);
		} catch (Exception e) {
			System.out.println("ERROR during mapping book to Mongo Object");
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		CreateBookCommand create = new CreateBookCommand();
		Book book = new Book();
		Author author = new Author();
		author.setFname("Gula");
		author.setLname("Nurmatova");
		CreateAuthorCommand createAuthor = new CreateAuthorCommand();
		String _id = createAuthor.execute(author);
		book.setTitle("Book2");
		book.setISBN("1234");
		book.set_author_id(_id);
		ArrayList<String> genres = new ArrayList<String>();
		genres.add("Comedy");
		genres.add("Humor");
		book.setGenres(genres);
		if (create.execute(book)) {
			System.out.println("SUCCESS:Book Created");
		} else {
			System.out.println("ERROR:Failed to create book");
		}

	}
}
