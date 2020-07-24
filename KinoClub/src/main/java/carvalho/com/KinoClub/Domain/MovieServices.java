package carvalho.com.KinoClub.Domain;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import carvalho.com.KinoClub.Domain.Models.Movies.Movie;
import carvalho.com.KinoClub.Persistence.FilmPersistence;

public class MovieServices {
	public Movie GetRandomMovie() throws JsonMappingException, JsonProcessingException {
		FilmPersistence persistence = new FilmPersistence();
		JSONObject MovieJson = persistence.GetRandomMovie();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

		Movie movie = objectMapper.readValue(MovieJson.toString(), Movie.class);
		return movie;
	}
}
