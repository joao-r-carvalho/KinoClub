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
			Movie1.accumulate("Description", "The Room is a 2003 American romantic drama film written, produced, and directed by Tommy Wiseau, who stars in the film alongside Juliette Danielle and Greg Sestero. The film centers on a melodramatic love triangle between amiable banker Johnny (Wiseau), his deceptive fiancée Lisa (Danielle), and his conflicted best friend Mark (Sestero). A significant portion of the film is dedicated to a series of unrelated subplots, most of which involve at least one supporting character and are left unresolved due to the film's inconsistent narrative structure. The work was reportedly intended to be semi-autobiographical in nature; according to Wiseau, the title alludes to the potential of a room to be the site of both good and bad events");
			Movie1.accumulate("Image","https://upload.wikimedia.org/wikipedia/en/e/e1/TheRoomMovie.jpg");
			
			Movie2.accumulate("Title", "Troll 2");
			Movie2.accumulate("Description", "Troll 2 is a 1990 supernatural comedy horror film directed by Claudio Fragasso[1] (under the pseudonym Drake Floyd) and starring Michael Stephenson, George Hardy, Margo Prey, Connie McFarland, Deborah Reed and Jason Wright. The plot concerns a family pursued by vegetarian goblins who seek to mutate them into plants so that they can eat them.");
			Movie2.accumulate("Image", "https://upload.wikimedia.org/wikipedia/en/9/9e/Troll_2_poster.jpg");
			
			Movie3.accumulate("Title", "Birdemic, Shock and Terror");
			Movie3.accumulate("Description", "Birdemic: Shock and Terror (often shortened to Birdemic) is a 2010 American independent romantic horror film[1] written, directed, and produced by James Nguyen, and starring Alan Bagh and Whitney Moore. Inspired by Alfred Hitchcock's The Birds, Birdemic tells the story of a romance between the two main characters as their small town is attacked by birds.");
			Movie3.accumulate("Image","https://upload.wikimedia.org/wikipedia/en/e/e0/Birdemic.jpg");

			MockDatabaseSingletonInstance =  new MockDatabaseSingleton();
			MockDatabaseSingletonInstance.MockMovieDatabase = new HashMap<UUID,JSONObject>();
			
			MockDatabaseSingletonInstance.MockMovieDatabase.put(UUID.fromString("0d366fed-be3e-44a8-a84c-9c0bdeafc238"), Movie1 );
			MockDatabaseSingletonInstance.MockMovieDatabase.put(UUID.fromString("ae743f02-8ee3-4205-bc6d-38ad0989a55d"), Movie2 );
			MockDatabaseSingletonInstance.MockMovieDatabase.put(UUID.fromString("216c6c39-986a-4d4a-a26b-89570607e8b1"), Movie3 );

		} 
		return MockDatabaseSingletonInstance;
		
	}



}
