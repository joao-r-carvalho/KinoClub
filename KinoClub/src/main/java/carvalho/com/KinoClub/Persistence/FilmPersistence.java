package carvalho.com.KinoClub.Persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class FilmPersistence {

	public JSONObject GetFilmByUUID(UUID id) {
		MockDatabaseSingleton singleton = MockDatabaseSingleton.GetInstance();
		return singleton.MockMovieDatabase.get(id);
	}

	public JSONObject GetRandomMovie() {
		MockDatabaseSingleton singleton = MockDatabaseSingleton.GetInstance();
		List<JSONObject> AllMovies = new ArrayList<JSONObject>(singleton.MockMovieDatabase.values());
		int IndexToFetch = new Random().nextInt(AllMovies.size());
		return AllMovies.get(IndexToFetch) ;
	}
	
	public List<String> GetAllMovies(){
		MongoCollection<Document> collection;
		try {
			collection = DBConnection.GetCollection("Movies","Movies");
			FindIterable<Document> Iterable = collection.find();
			MongoCursor<Document> cursor = Iterable.cursor();
			List<String> AllMovies = new ArrayList<String>();
			while(cursor.hasNext()) {			
				Document currentDocument = cursor.next();
				AllMovies.add(currentDocument.toJson());
	 		}
			return AllMovies;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		
	}


}
