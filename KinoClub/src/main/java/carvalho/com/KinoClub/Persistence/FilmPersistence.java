package carvalho.com.KinoClub.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
public class FilmPersistence {

	public String GetFilmByUUID(String id) {
		
		try {
			MongoCollection<Document> collection = DBConnection.GetCollection("Movies","Movies");
			Bson bsonFilter = Filters.eq("_id", new ObjectId(id) );

			Document document = collection.find(bsonFilter).first();
			return document.toJson();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Couldn't find the movie with id " + id;
		}
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
