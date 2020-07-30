package carvalho.com.KinoClub.Persistence;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBConnection {
	private static DBConnection DatabaseSingleton;

	public static MongoClient MovieDatabase;

	private static synchronized DBConnection GetInstance() {
		if (DatabaseSingleton == null) {
			MongoClientURI uri = new MongoClientURI(
					"mongodb+srv://EditUser:MongoDBEditUser@kinoclubpersistence.wqz7a.azure.mongodb.net/Movies?retryWrites=true&w=majority");

			MongoClient mongoClient = new MongoClient(uri);
			DatabaseSingleton = new DBConnection();

			MovieDatabase = mongoClient;
		}
		return DatabaseSingleton;

	}
	
	public static MongoCollection<Document> GetCollection(String DatabaseName, String CollectionName) {
		DBConnection connection = DBConnection.GetInstance();
		MongoDatabase database = connection.MovieDatabase.getDatabase(DatabaseName);
		MongoCollection<Document> collection = database.getCollection(CollectionName);
		return collection;
 		
	}
}
