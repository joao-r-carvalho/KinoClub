package carvalho.com.KinoClub.Domain.Services;

import java.util.ArrayList;

import carvalho.com.KinoClub.Domain.Models.Movies.Movie;
import carvalho.com.KinoClub.Domain.Models.Users.FavoriteMovieList;
import carvalho.com.KinoClub.Domain.Models.Users.User;
import carvalho.com.KinoClub.Domain.Models.Users.UserProfile;
import carvalho.com.KinoClub.Persistence.FilmPersistence;
import carvalho.com.KinoClub.Persistence.UserPersistence;

public class UserServices {
	public ArrayList<Movie> GetUserFavoriteMovies(User User) {
		UserPersistence Users = new UserPersistence();
		FilmPersistence Films = new FilmPersistence();
		//The ids are getting fetched properly but the are not being recognized by the Bson filter 
		FavoriteMovieList MovieIds = Users.GetFavoriteMovieIds(User.UserId);
		 
		ArrayList<Movie> Movies = Films.GetMoviesByID(MovieIds.MovieIdentifiers);
		return Movies;
	}

	public void AddMovieToUserFavorites(User user, String MovieIdentifier) {
		UserPersistence Users = new UserPersistence();
		Users.AddToFavorites(user.UserId, MovieIdentifier);

	}

	public UserProfile GetUserProfile(String userId) {
		UserProfile profile = new UserProfile();
		User stub = new User();
		stub.UserId = userId;
		profile.favoriteMovies = GetUserFavoriteMovies(stub);
		return profile;
		
	}
	
	
}
