package carvalho.com.KinoClub.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.util.JSON;

import carvalho.com.KinoClub.Domain.Models.Movies.Movie;
import carvalho.com.KinoClub.Persistence.DBConnection;
import carvalho.com.KinoClub.Persistence.FilmPersistence;

public class MovieServices {

	public Movie GetRandomMovie() throws JsonMappingException, JsonProcessingException {
		
		FilmPersistence persistence = new FilmPersistence();
		return persistence.GetRandomMovie();
	//	ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		//Movie movie = objectMapper.readValue(MovieJson.toString(), Movie.class);
	//	return movie;
	}

	public JSONObject GetRandomMovieJSON() {
		
		FilmPersistence persistence = new FilmPersistence();
		return new JSONObject(JSON.serialize(persistence.GetRandomMovie()));
		 
		
		
	}

	public String GetRandomMovieFromDatabase() {
		
		FilmPersistence p = new FilmPersistence();
		List<String> Movies = p.GetAllMovies();
		int IndexToFetch = new Random().nextInt(Movies.size());
		return Movies.get(IndexToFetch);

	}
	public Movie GetMovieFromDatabase(String id) {
		FilmPersistence persistence = new FilmPersistence();
		return persistence.GetFilmByUUID(id);
	}
	public List<String> GetAllMovies() {
		FilmPersistence persistence = new FilmPersistence();
		return persistence.GetAllMovies();
		
	}
	public ArrayList<Movie> GetAllMoviesWellTyped(){
		FilmPersistence persistence = new FilmPersistence();
		return persistence.GetAllMoviesWellTyped();
		
	}
	
}
