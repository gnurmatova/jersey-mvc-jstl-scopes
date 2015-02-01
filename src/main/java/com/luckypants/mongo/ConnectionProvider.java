package com.luckypants.mongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class ConnectionProvider {
	/**
	 * TODO:modify this method to allow passing the collection name to it
	 * @return
	 */

	public DBCollection getCollection(String collectionName) {
		try {

			MongoClient mongo = new MongoClient("oceanic.mongohq.com", 10099);

			DB db = mongo.getDB("luckypants");
			if (db == null) {
				System.out.println("Could not connect to Database");
			}

			boolean auth = db.authenticate("unh", "unh".toCharArray());
			if (auth == false) {
				System.out.println("Could not authenticate");
			}

			DBCollection booksColl = db.getCollection(collectionName);
			return booksColl;

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;

	}


}
