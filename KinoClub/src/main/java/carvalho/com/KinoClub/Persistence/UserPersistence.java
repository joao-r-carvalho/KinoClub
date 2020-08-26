package carvalho.com.KinoClub.Persistence;

import java.util.ArrayList;

import javax.xml.crypto.dsig.spec.XPathType.Filter;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import carvalho.com.KinoClub.Domain.Models.Users.FavoriteMovieList;
import carvalho.com.KinoClub.Domain.Models.Users.User;

public class UserPersistence {
	public static Boolean RegisterUser(User user) {
		try {
			MongoDatabase Database = DBConnection.GetDatabase("Movies");
			MongoCollection<User> Users = Database.getCollection("Users", User.class);
			Users.insertOne(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static User GetUser(String username) {
		try {
			MongoCollection<Document> collection = DBConnection.GetCollection("Movies", "Users");
			Bson bsonFilter = Filters.eq("Username", username);
			return collection.find(bsonFilter, User.class).first();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static User GetUserByUserId(String UserID) {
		try {
			MongoCollection<Document> collection = DBConnection.GetCollection("Movies", "Users");
			Bson bsonFilter = Filters.eq("UserId", UserID);
			return collection.find(bsonFilter, User.class).first();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static Boolean DoesUsernameExist(String username) {
		try {
			MongoCollection<Document> collection = DBConnection.GetCollection("Movies", "Users");
			Bson bsonFilter = Filters.eq("Username", username);
			return collection.countDocuments(bsonFilter) > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	public FavoriteMovieList GetFavoriteMovieIds(String userId) {
		try {
			MongoDatabase Database = DBConnection.GetDatabase("Movies");
			MongoCollection<FavoriteMovieList> FavoriteMovies = Database.getCollection("UsersFavoriteMovies",
					FavoriteMovieList.class);
			Bson bsonFilter = Filters.eq("UserId", userId);
			return FavoriteMovies.find(bsonFilter).first();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void AddToFavorites(String userId, String movieIdentifier) {
		MongoDatabase Database;
		try {
			Database = DBConnection.GetDatabase("Movies");
			MongoCollection<FavoriteMovieList> FavoriteMovies = Database.getCollection("UsersFavoriteMovies",
					FavoriteMovieList.class);
			FavoriteMovieList list = GetFavoriteMovieIds(userId);
			if (list != null && list.MovieIdentifiers != null && !list.MovieIdentifiers.contains(movieIdentifier)) {
				Bson userIdFilter = Filters.eq("UserId" , userId);
				Bson update = Updates.addToSet("MovieIdentifiers", movieIdentifier);
 				FavoriteMovies.updateOne(userIdFilter, update); 

			} else {
				FavoriteMovieList Favorites = new FavoriteMovieList();
				Favorites.MovieIdentifiers = new ArrayList<String>();
				Favorites.MovieIdentifiers.add(movieIdentifier);
				Favorites.UserId = userId;
				FavoriteMovies.insertOne(Favorites);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void RemoveFromFavorites(String userId, String movieIdentifier) {
		MongoDatabase Database;
		try {
			Database = DBConnection.GetDatabase("Movies");
			MongoCollection<FavoriteMovieList> FavoriteMovies = Database.getCollection("UsersFavoriteMovies",
					FavoriteMovieList.class);
			FavoriteMovieList list = GetFavoriteMovieIds(userId);
			if (list != null && list.MovieIdentifiers != null && list.MovieIdentifiers.contains(movieIdentifier)) {
				Bson userIdFilter = Filters.eq("UserId" , userId );
				Bson update = Updates.pull("MovieIdentifiers", movieIdentifier);
 				FavoriteMovies.updateMany(userIdFilter,update);

			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	

}
