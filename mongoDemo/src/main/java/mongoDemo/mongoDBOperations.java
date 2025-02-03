package mongoDemo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;

public class mongoDBOperations {
	public static void main(String args[])
	{	
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		
		MongoDatabase database = mongoClient.getDatabase("demodb");
		
		MongoCollection<Document> collection = database.getCollection("Employee");
	
		// Retrive
//		FindIterable<Document> i = collection.find();
//		for(Document d : i)
//		{
//			System.out.println(d);
//			System.out.println(d.toJson());
//		}
		
		// Insert 1st Method
//		Document doc = new Document();
//		doc.append("Name", "Karan");
//		doc.append("age", 21);
//		doc.append("salary", 60000);
//		doc.append("designation", "Programmer");
//		
//		collection.insertOne(doc);
		
		// Insert 2nd Method
//		collection.insertOne(new Document().append("name", "madhav").append("age",21).append("salary", 25000).append("designation", "Manager"));
		
		// Insert 3rd Method
//		List<Document> empList = new ArrayList<Document>();
//		empList.add(new Document().append("name", "madhav").append("age",21).append("salary", 25000).append("designation", "Manager"));
//		empList.add(new Document().append("name", "sanat").append("age",22).append("salary", 23000).append("designation", "Programmer"));
//		empList.add(new Document().append("name", "ramesh").append("age",26).append("salary", 30222).append("designation", "Clerk"));
//		empList.add(new Document().append("name", "suresh").append("age",31).append("salary", 45000).append("designation", "Manager"));
//		
//		collection.insertMany(empList);
		
		/*
		// to remove id from the query
		Bson projection1 = Projections.excludeId();
		// to remove multiple columns from the query
		Bson projection2 = Projections.exclude("_id","age","salary");		
		
		// to add filter while executing query
		Bson filter = Filters.eq("designation", "Programmer");
		
		// For sorting resultant data
		Bson asort = Sorts.ascending("salary");
		Bson dsort = Sorts.descending("age","salary");
		
		FindIterable<Document> i = collection.find(filter).projection(projection1).sort(dsort);    //compulsory to remove some columns
		for(Document d : i) {
			System.out.println(d.toJson());
		}
		*/
		
		// To update the data with some filters
		Bson filter = Filters.lte("age", 35);
		Bson update = Updates.set("designation", "Manager");
		
		collection.updateMany(filter,update);
		
		// Delete query with filter in above
		Bson filter2 = Filters.eq("name","Raman");
		collection.deleteOne(filter2);
		
		System.out.println("Successfully did the operation on Mongo DB......");
		mongoClient.close();
	}
}
