package com.epam.ts.dao;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("mongoUserDao")
public class MongoUserDao implements UserDao {
    MongoClient mongoClient;
    MongoDatabase database;
    @PostConstruct
    public void init()
    {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("user");

    }
    @Override
    public String getUser(String id) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", id);
        MongoCollection collection = database.getCollection("user");
        FindIterable<Document> result = collection.find(searchQuery);

        return JSON.serialize(result);
    }
}
