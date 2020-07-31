package carvalho.com.KinoClub.Persistence;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBConnection {
	private static DBConnection DatabaseSingleton;

	public static MongoClient MovieDatabase;
	
	public static synchronized DBConnection GetInstance(String MongoURI) {
		if (DatabaseSingleton == null) {
			MongoClientURI uri = new MongoClientURI(MongoURI);
			MongoClient mongoClient = new MongoClient(uri);
			DatabaseSingleton = new DBConnection();

			MovieDatabase = mongoClient;
		}
		return DatabaseSingleton;
	}
	
	private static synchronized DBConnection GetInstance() throws Exception {
		if (DatabaseSingleton == null) {
			throw new Exception("Mongo db connection was not initiated, GetInstance needs to be called with target mongo URI first");
		}
		return DatabaseSingleton;
		
	}
	
	
	public static MongoCollection<Document> GetCollection(String DatabaseName, String CollectionName) throws Exception {
		DBConnection connection = DBConnection.GetInstance();
		MongoDatabase database = connection.MovieDatabase.getDatabase(DatabaseName);
		MongoCollection<Document> collection = database.getCollection(CollectionName);
		return collection;
 		
	}
}
