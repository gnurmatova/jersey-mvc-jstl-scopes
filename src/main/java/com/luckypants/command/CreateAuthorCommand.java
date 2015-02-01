package com.luckypants.command;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.luckypants.model.Author;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class CreateAuthorCommand {

	public String execute(Author author) {
		ConnectionProvider conn = new ConnectionProvider();
		DBCollection collection = conn.getCollection("authors");

		ObjectMapper mapper = new ObjectMapper();
		try {
			DBObject dbObject = (DBObject) JSON.parse(mapper
					.writeValueAsString(author));
			collection.insert(dbObject);
			return dbObject.get("_id").toString();
			
		} catch (Exception e) {
			System.out.println("ERROR during mapping author to Mongo Object");
			return null;
		}
	}

	public static void main(String[] args) {
		CreateAuthorCommand create = new CreateAuthorCommand();
		Author author = new Author();
		author.setFname("Gula");
		author.setLname("Nurmatova");
		Object id = create.execute(author);
		if ( id!=null) {
			System.out.println("SUCCESS:Author Created:"+id);
		} else {
			System.out.println("ERROR:Failed to create author");
		}

	}
}
