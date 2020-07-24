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
			JSONObject Movie2= new JSONObject();
			JSONObject Movie3= new JSONObject();
			
			Movie1.accumulate("Title", "The Room");
			Movie2.accumulate("Title", "Troll 2");
			Movie3.accumulate("Title", "Birdemic, Shock and Terror");
			
			MockDatabaseSingletonInstance =  new MockDatabaseSingleton();
			MockDatabaseSingletonInstance.MockMovieDatabase = new HashMap<UUID,JSONObject>();
			
			MockDatabaseSingletonInstance.MockMovieDatabase.put(UUID.fromString("0d366fed-be3e-44a8-a84c-9c0bdeafc238"), Movie1 );
			MockDatabaseSingletonInstance.MockMovieDatabase.put(UUID.fromString("ae743f02-8ee3-4205-bc6d-38ad0989a55d"), Movie2 );
			MockDatabaseSingletonInstance.MockMovieDatabase.put(UUID.fromString("216c6c39-986a-4d4a-a26b-89570607e8b1"), Movie3 );

		} 
		return MockDatabaseSingletonInstance;
		
	}



}
