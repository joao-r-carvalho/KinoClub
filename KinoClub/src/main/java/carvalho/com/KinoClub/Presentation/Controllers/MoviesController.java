package carvalho.com.KinoClub.Presentation.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import carvalho.com.KinoClub.Domain.MovieServices;
import carvalho.com.KinoClub.Domain.Models.Movies.Movie;
import carvalho.com.KinoClub.Persistence.FilmPersistence;

import java.util.UUID;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class MoviesController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping(value = "/Movies/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String FetchMovieById(@PathVariable String id) {
		UUID ParsedID = UUID.fromString(id);
		FilmPersistence persistence = new FilmPersistence();
		JSONObject object = persistence.GetFilmByUUID(ParsedID);
		return object.toString();
	}

	@RequestMapping(value = "/Movies/Random", method = RequestMethod.GET)
	@ResponseBody
	public String GetRandomMovie() {
		MovieServices movies = new MovieServices();

		try {
			Movie movie = movies.GetRandomMovie();
			return movie.toString();
		} catch (JsonProcessingException e) {
			return "Couldn't find you a movie";
		}

	}
}