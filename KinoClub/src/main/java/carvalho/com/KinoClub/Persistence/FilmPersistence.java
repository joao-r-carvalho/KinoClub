package carvalho.com.KinoClub.Persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import org.json.JSONObject;

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

}
