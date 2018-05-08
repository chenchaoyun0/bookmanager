 package com.sttx.bookmanager.test;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnTest {
     public static void main(String[] args) {
         ServerAddress serverAddress = new ServerAddress("39.107.126.75",27017);
         List<ServerAddress> seeds = new ArrayList<ServerAddress>();
         seeds.add(serverAddress);
         MongoCredential credentials = MongoCredential.createScramSha1Credential("root", "bookmanager", "root123".toCharArray());
         List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
         credentialsList.add(credentials);
         MongoClient client = new MongoClient(seeds);//no auth
//         MongoClient client = new MongoClient(seeds, credentialsList);
         MongoDatabase db = client.getDatabase("bookmanager");
         // MongoIterable<Document> collections=db.listCollections();
         MongoCollection<org.bson.Document> collection = db.getCollection("bookmanager");
         ArrayList<Document> into = collection.find().into(new ArrayList<Document>());
         System.out.println(into);
    }
}
