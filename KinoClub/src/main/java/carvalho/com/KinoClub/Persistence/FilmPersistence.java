package carvalho.com.KinoClub.Persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import carvalho.com.KinoClub.Domain.Models.Movies.Movie;
import carvalho.com.KinoClub.Domain.Models.Users.FavoriteMovieList;

public class FilmPersistence {

	public Movie GetFilmByUUID(String id) {

		try {
			MongoCollection<Document> collection = DBConnection.GetCollection("Movies", "Movies");
			Bson bsonFilter = Filters.eq("MovieIdentifier", id);
			return collection.find(bsonFilter, Movie.class).first();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Movie error = new Movie();
			error.Description = "Oops couldn't find your movie";
			return error;
		}
	}

	public Boolean InsertMovie(Movie movie) {
		MongoDatabase data;
		try {
			data = DBConnection.GetDatabase("Movies");
			MongoCollection<Movie> movies = data.getCollection("Movies", Movie.class);
			movies.insertOne(movie);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public Boolean UpdateMovie(String MovieIdentifier, Movie movie) {
		return true;
	}

	public Movie GetRandomMovie() {

		try {
			MongoCollection<Document> collection = DBConnection.GetCollection("Movies", "Movies");
			// List<Movie> document =
			// collection.aggregate(Arrays.asList(Aggregates.sample(1),Movie.class);
			return collection.find(Movie.class).first();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<Movie> GetAllMoviesWellTyped() {

		try {
			MongoCollection<Document> collection = DBConnection.GetCollection("Movies", "Movies");
			// List<Movie> document =
			// collection.aggregate(Arrays.asList(Aggregates.sample(1),Movie.class);
			return collection.find(Movie.class).into(new ArrayList<Movie>());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public List<String> GetAllMovies() {
		MongoCollection<Document> collection;
		try {
			collection = DBConnection.GetCollection("Movies", "Movies");
			FindIterable<Document> Iterable = collection.find();
			MongoCursor<Document> cursor = Iterable.cursor();
			List<String> AllMovies = new ArrayList<String>();
			while (cursor.hasNext()) {
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

	public ArrayList<Movie> GetMoviesByID(ArrayList<String> movieIdentifiers) {
		try {
			MongoCollection<Document> collection = DBConnection.GetCollection("Movies", "Movies");
			// List<Movie> document =
			// collection.aggregate(Arrays.asList(Aggregates.sample(1),Movie.class);
			Bson filter = Filters.in("MovieIdentifier",movieIdentifiers);
			return collection.find(filter,Movie.class).into(new ArrayList<Movie>());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}



}
