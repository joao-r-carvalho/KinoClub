package carvalho.com.KinoClub.Presentation.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import carvalho.com.KinoClub.Domain.MovieServices;
import carvalho.com.KinoClub.Domain.Models.Movies.Movie;
import carvalho.com.KinoClub.Persistence.FilmPersistence;

import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class MoviesController {

	
	@RequestMapping(value = "/Movies/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String FetchMovieById(@PathVariable String id) {
		UUID ParsedID = UUID.fromString(id);
		FilmPersistence persistence = new FilmPersistence();
		JSONObject object = persistence.GetFilmByUUID(ParsedID);
		return object.toString();
	}

	/*@CrossOrigin(origins = "*")
	@RequestMapping(value = "/Movies/Random", method = RequestMethod.GET)
	@ResponseBody
	public String GetRandomMovie() {
		MovieServices movies = new MovieServices();
		return movies.GetRandomMovieJSON().toString();
	}*/

	@RequestMapping(value = "/Movies/Random/Simple", method = RequestMethod.GET)
	@ResponseBody
	public String GetRandomMovieSimple() {
		MovieServices movies = new MovieServices();

		try {
			Movie movie = movies.GetRandomMovie();
			return movie.toString();
		} catch (JsonProcessingException e) {
			return "Couldn't find you a movie";
		}

	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/Movies/Random", method = RequestMethod.GET)
	@ResponseBody
	public String Test() {
		MovieServices movies = new MovieServices();
		String MovieJSON = movies.GetRandomMovieFromDatabase();
		return MovieJSON;
		 

	}

}