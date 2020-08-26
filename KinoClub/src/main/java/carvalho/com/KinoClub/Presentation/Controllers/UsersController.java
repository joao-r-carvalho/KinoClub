package carvalho.com.KinoClub.Presentation.Controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import carvalho.com.KinoClub.Domain.Models.Movies.Movie;
import carvalho.com.KinoClub.Domain.Models.Users.User;
import carvalho.com.KinoClub.Domain.Models.Users.UserProfile;
import carvalho.com.KinoClub.Domain.Services.UserServices;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Users")
@Api(tags = "User preferences")
@CrossOrigin(origins = { "http://localhost:3000", "https://joao-r-carvalho.github.io","https://kinoclub-1595860726231.azurewebsites.net"  }, allowCredentials = "true")
public class UsersController extends BaseController {
	@RequestMapping(value = "/Me", method = RequestMethod.GET)
	public UserProfile Profile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = (User) request.getAttribute("AuthenticatedUser");
		if (user == null) {
			response.sendError(401, "Please login first");
			return null;

		}
		UserServices services = new UserServices();
		return services.GetUserProfile(user);

	}

	@RequestMapping(value = "/AddToFavorites", method = RequestMethod.POST)
	@ResponseBody
	public UserProfile AddToFavorites(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String MovieIdentifier) throws IOException {
		User user = (User) request.getAttribute("AuthenticatedUser");
		if (user == null) {
			response.sendError(401, "Please login first");
			return null;

		}
		UserServices users = new UserServices();
		users.AddMovieToUserFavorites(user, MovieIdentifier);
		return users.GetUserProfile(user);

	}
	@RequestMapping(value = "/RemoveFromFavorites", method = RequestMethod.DELETE)
	@ResponseBody
	public UserProfile RemoveFromFavorites(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String MovieIdentifier) throws IOException {
		User user = (User) request.getAttribute("AuthenticatedUser");
		if (user == null) {
			response.sendError(401, "Please login first");
			return null;

		}
		UserServices users = new UserServices();
		users.RemoveMovieFromUserFavorites(user,MovieIdentifier);
		return users.GetUserProfile(user);

	}

	@RequestMapping(value = "/IsMovieFavorite/{MovieIdentifier}", method = RequestMethod.GET)
	@ResponseBody
	public boolean IsMovieFavorite(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String MovieIdentifier) throws IOException {
		User user = (User) request.getAttribute("AuthenticatedUser");
		if (user == null) {
			response.sendError(401, "Please login first");
			return false;

		}
		UserServices users = new UserServices();
		return users.IsMovieFavorite(user, MovieIdentifier);
 
	}

}
