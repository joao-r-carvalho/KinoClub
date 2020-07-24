package carvalho.com.KinoClub.Persistence;

import java.util.HashMap;
import java.util.UUID;

import org.json.JSONObject;

public class MockDatabaseSingleton {
	
	private static MockDatabaseSingleton MockDatabaseSingletonInstance;
	
	public  static HashMap<UUID,JSONObject> MockMovieDatabase;
 
	public static synchronized MockDatabaseSingleton GetInstance() {
		if(MockDatabaseSingletonInstance == null) {
			JSONObject Movie1= new JSONObject();
			Movie1.accumulate("Title", "The Room");
			MockDatabaseSingletonInstance =  new MockDatabaseSingleton();
			MockDatabaseSingletonInstance.MockMovieDatabase = new HashMap<UUID,JSONObject>();
			MockDatabaseSingletonInstance.MockMovieDatabase.put(UUID.fromString("0d366fed-be3e-44a8-a84c-9c0bdeafc238"), Movie1 );
			 
		} 
		return MockDatabaseSingletonInstance;
		
	}



}
