package com.luckypants.command;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luckypants.model.Author;
import com.luckypants.model.Book;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ListAllBooksCommand {
	ObjectMapper mapper = new ObjectMapper();

	public ArrayList<Book> execute() {
		ConnectionProvider conn = new ConnectionProvider();
		DBCollection booksCollection = conn.getCollection("books");

		DBCursor cursor = booksCollection.find();

		ArrayList<Book> books = new ArrayList<Book>();
		// GetBookCommand getBook = new GetBookCommand();
		BasicDBObject searchQuery = new BasicDBObject();
		try {
			while (cursor.hasNext()) {
				Book b = mapper.readValue(cursor.next().toString(), Book.class);
				if (b.get_author_id() != null) {
					
					DBCollection authorsCollection = conn
							.getCollection("authors");
					searchQuery = new BasicDBObject();
					searchQuery.put("_id", new ObjectId(b.get_author_id()));

					DBObject author = authorsCollection.findOne(searchQuery);
					
					b.setAuthor(mapper.readValue(author.toString(),
							Author.class));
				}
				System.out.println(b.getAuthor().getLname());
				books.add(b);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			cursor.close();
		}
		return books;

	}

	public static void main(String[] args) {
		ListAllBooksCommand listBooks = new ListAllBooksCommand();
		ArrayList<Book> list = listBooks.execute();
		System.out.println(list);

	}
}
