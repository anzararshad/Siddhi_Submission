import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class mongo {
	

	public static void main(String[] args) {
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB database = mongoClient.getDB("test");
		mongoClient.getDatabaseNames().forEach(System.out::println);
		database.createCollection("Question1", null);
		
	}
	
public void FirstQuest(long timeStamp,String CountOfCarTypes,String Make) {
		
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB database = mongoClient.getDB("test");
		DBCollection collection = database.getCollection("Question1");
		BasicDBObject document = new BasicDBObject();
		document.put("siddhiTime", timeStamp);
		document.put("QuestionNo", 1);
		document.put("Violation_code", CountOfCarTypes);
		document.put("Ticket_number", Make);
		
		collection.insert(document);
		System.out.println("Succesfully Added to secondQuest"+CountOfCarTypes+Make);
		
	}
	
	public void secondQuest(long timeStamp,String Violation_code,String Ticket_number,String Fine_amount) {
		
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB database = mongoClient.getDB("test");
		DBCollection collection = database.getCollection("Question1");
		BasicDBObject document = new BasicDBObject();
		document.put("siddhiTime", timeStamp);
		document.put("QuestionNo", 2);
		document.put("Violation_code", Violation_code);
		document.put("Ticket_number", Ticket_number);
		document.put("Fine_amount", Double.parseDouble(Fine_amount));
		
		collection.insert(document);
		System.out.println("Succesfully Added to secondQuest"+Violation_code+Ticket_number+Fine_amount);
		
	}
	
public void thirdQuest(long timeStamp,String Violation_code,String countViolation,String Violation_Description) {
			
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB database = mongoClient.getDB("test");
		DBCollection collection = database.getCollection("Question1");
		BasicDBObject document = new BasicDBObject();
		document.put("siddhiTime", timeStamp);
		document.put("QuestionNo", 3);
		document.put("Violation_code", Violation_code);
		document.put("countViolation", countViolation);
		document.put("Violation_Description", Violation_Description);
		
		collection.insert(document);
		System.out.println("Succesfully Added to secondQuest"+Violation_code+countViolation+Violation_Description);
		
	}


public void fourth(long timeStamp,String maxFine_amoun,String RP_State_Plate) {
	
	MongoClient mongoClient = new MongoClient("localhost", 27017);
	DB database = mongoClient.getDB("test");
	DBCollection collection = database.getCollection("Question1");
	BasicDBObject document = new BasicDBObject();
	document.put("siddhiTime", timeStamp);
	document.put("QuestionNo", 4);
	document.put("maxFine_amoun", maxFine_amoun);
	document.put("RP_State_Plate", RP_State_Plate);
	
	collection.insert(document);
	System.out.println("Succesfully Added to secondQuest"+maxFine_amoun+RP_State_Plate);
	
}

}
