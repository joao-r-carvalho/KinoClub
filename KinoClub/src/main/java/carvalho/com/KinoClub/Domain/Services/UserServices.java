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
		// The ids are getting fetched properly but the are not being recognized by the
		// Bson filter
		FavoriteMovieList MovieIds = Users.GetFavoriteMovieIds(User.UserId);
		ArrayList<Movie> Movies = new ArrayList<Movie>();
		if (MovieIds != null) {
			Movies = Films.GetMoviesByID(MovieIds.MovieIdentifiers);

		}
		return Movies;

	}

	public void AddMovieToUserFavorites(User user, String MovieIdentifier) {
		UserPersistence Users = new UserPersistence();
		Users.AddToFavorites(user.UserId, MovieIdentifier);

	}

	public UserProfile GetUserProfile(User user) {
		UserProfile profile = new UserProfile();
		profile.user = user;
		profile.favoriteMovies = GetUserFavoriteMovies(user);
		return profile;
	}

	public void RemoveMovieFromUserFavorites(User user, String MovieIdentifier) {
		UserPersistence Users = new UserPersistence();
		Users.RemoveFromFavorites(user.UserId, MovieIdentifier);
	}

	public boolean IsMovieFavorite(User user, String movieIdentifier) {
		UserPersistence Users = new UserPersistence();
		FavoriteMovieList favoriteMovies = Users.GetFavoriteMovieIds(user.UserId);
		return favoriteMovies != null && favoriteMovies.MovieIdentifiers != null
				&& favoriteMovies.MovieIdentifiers.contains(movieIdentifier);
	}

}
