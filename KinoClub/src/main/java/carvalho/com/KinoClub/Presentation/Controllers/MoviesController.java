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
import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "Movie information")
@RestController
@RequestMapping("/Movies")
public class MoviesController extends BaseController {

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String FetchMovieById(@PathVariable String id) {
		MovieServices movieServices = new MovieServices();
		String object = movieServices.GetMovieFromDatabase(id);
		return object;
	}

	@RequestMapping(value = "/Random/Simple", method = RequestMethod.GET)
	@ResponseBody
	public Movie GetRandomMovieSimple() {
		MovieServices movies = new MovieServices();

		try {
			Movie movie = movies.GetRandomMovie();
			return movie;
		} catch (JsonProcessingException e) {
			return null;
		}

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/Random", method = RequestMethod.GET)
	@ResponseBody
	public String GetRandomMovie() {
		MovieServices movies = new MovieServices();
		String MovieJSON = movies.GetRandomMovieFromDatabase();
		return MovieJSON;

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/All", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Movie> ListAllMovies() {
		MovieServices movies = new MovieServices();

		return movies.GetAllMoviesWellTyped();

	}

}