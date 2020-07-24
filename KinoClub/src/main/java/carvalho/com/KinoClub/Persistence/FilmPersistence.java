package carvalho.com.KinoClub.Persistence;

import java.util.HashMap;
import java.util.UUID;

import org.json.JSONObject;

public class FilmPersistence {

	public JSONObject GetFilmByUUID(UUID id) {
		MockDatabaseSingleton singleton = MockDatabaseSingleton.GetInstance();
		return singleton.MockMovieDatabase.get(id);
	}

}
