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

	public void AddMovieToUserFavorites(String AuthenticationToken, String MovieIdentifier) {
		AuthenticationServices s = new AuthenticationServices();
		User u = s.GetUserFromToken(AuthenticationToken);
		AddMovieToUserFavorites(u, MovieIdentifier);
	}

	public void AddMovieToUserFavorites(User user, String MovieIdentifier) {
		UserPersistence Users = new UserPersistence();
		Users.AddToFavorites(user.UserId, MovieIdentifier);

	}

	public UserProfile GetUserProfile(String JWT) {
		UserProfile profile = new UserProfile();
		AuthenticationServices s = new AuthenticationServices();
		User u = s.GetUserFromToken(JWT);
		profile.user = u;
		profile.favoriteMovies = GetUserFavoriteMovies(u);
		return profile;

	}

}
