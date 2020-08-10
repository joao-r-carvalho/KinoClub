package carvalho.com.KinoClub;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.json.JSONObject;

import carvalho.com.KinoClub.Domain.MovieServices;
import carvalho.com.KinoClub.Domain.Models.Movies.Movie;
import carvalho.com.KinoClub.Persistence.FilmPersistence;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

class MovieTests {


    @Test
    void GetRandomMovieFromPersistenceSuccess() {
		FilmPersistence persistence = new FilmPersistence();
		Movie object = persistence.GetRandomMovie();
		assertNotNull(object);
		
    }
    @Test
    void GetRandomMovieFromDomainSuccess() throws JsonMappingException, JsonProcessingException {
		MovieServices movieServices = new MovieServices();
		Movie movie = movieServices.GetRandomMovie();
		assertNotNull(movie);
		
    }

}