package carvalho.com.KinoClub.Presentation.Controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import carvalho.com.KinoClub.Domain.Services.MovieServices;
import carvalho.com.KinoClub.Domain.Services.UserServices;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Users")
@Api(tags = "User preferences")
public class UsersController extends BaseController {
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/Me", method = RequestMethod.GET)
	public UserProfile Profile(@RequestHeader @RequestParam(required = false) @CookieValue("KCAuthentication") String AuthenticationToken, HttpServletResponse response) throws IOException {
		
		if (AuthenticationToken == null) {
			response.sendError(401, "Please login first");
			return null;

		}
		UserServices services = new UserServices();
		return services.GetUserProfile(AuthenticationToken);

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/Users/Movies", method = RequestMethod.POST)
	@ResponseBody
	public UserProfile AddToFavorites(@CookieValue(value = "KCAuthentication") String AuthenticationToken,
			@RequestBody String MovieIdentifier) {
		UserServices users = new UserServices();
		users.AddMovieToUserFavorites(AuthenticationToken, MovieIdentifier);
		return users.GetUserProfile(AuthenticationToken);

	}

}
