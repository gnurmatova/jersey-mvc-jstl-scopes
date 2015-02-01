package com.luckypants.command;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luckypants.model.Author;
import com.luckypants.model.Book;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class GetBookCommand {
	ObjectMapper mapper = new ObjectMapper();

	// modify signature of this method to now return the Book, not DBObject
	public Book execute(String key, String value) {
		ConnectionProvider conn = new ConnectionProvider();
		DBCollection booksCollection = conn.getCollection("books");

		BasicDBObject searchQuery = new BasicDBObject();
		if (key.equals("_id")) {
			searchQuery.put(key, new ObjectId(value));
		} else {
			searchQuery.put(key, value);
		}

		DBObject book = booksCollection.findOne(searchQuery);

		Book vBook = null;
		// Now find the Author
		try {
			vBook = mapper.readValue(book.toString(), Book.class);
			DBCollection authorsCollection = conn.getCollection("authors");
			searchQuery = new BasicDBObject();
			searchQuery.put("_id", new ObjectId(vBook.get_author_id()));

			DBObject author = authorsCollection.findOne(searchQuery);
			vBook.setAuthor(mapper.readValue(author.toString(), Author.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vBook;
	}

	public static void main(String[] args) {
		GetBookCommand command = new GetBookCommand();

		Book b = command.execute("isbn", "1234");
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
