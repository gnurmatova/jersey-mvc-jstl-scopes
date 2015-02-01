package com.luckypants.command;

import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class DeleteBookCommand {

	public boolean execute(String isbn) {
		ConnectionProvider booksConn = new ConnectionProvider();
		DBCollection booksCollection = booksConn.getCollection("books");

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("isbn", isbn);

		//DBCursor cursor = booksCollection.find(searchQuery);

		booksCollection.remove(searchQuery);

		return true;
	}

	public static void main(String[] args) {
		DeleteBookCommand command = new DeleteBookCommand();
		boolean result =  command.execute("h");
		System.out.println(result);
	}

}
