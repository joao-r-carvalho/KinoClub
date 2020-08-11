package carvalho.com.KinoClub.Presentation.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import carvalho.com.KinoClub.Domain.Models.Movies.Movie;
import carvalho.com.KinoClub.Domain.Services.MovieServices;
import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "Movie information")
@RestController
@RequestMapping("/Movies")
@CrossOrigin(origins = "*")
public class MoviesController extends BaseController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Movie FetchMovieById(@PathVariable String id) {
		MovieServices movieServices = new MovieServices();
		Movie movie =movieServices.GetMovieFromDatabase(id);
		return movie;
	}

	@RequestMapping(value = "/Random", method = RequestMethod.GET)
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
	@RequestMapping(value = "/All", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Movie> ListAllMovies() {
		MovieServices movies = new MovieServices();

		return movies.GetAllMoviesWellTyped();

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/Movies/New", method = RequestMethod.POST)
	@ResponseBody
	public Boolean InsertMovie(@RequestBody Movie movie) {
		MovieServices movies = new MovieServices();

		return movies.InsertMovie(movie);

	}
	
	
	

}