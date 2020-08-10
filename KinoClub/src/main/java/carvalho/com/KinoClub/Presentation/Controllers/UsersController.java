package carvalho.com.KinoClub.Presentation.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@ResponseBody
	public UserProfile Profile(String userId) {
		UserServices services = new UserServices();
		return services.GetUserProfile(userId);
 	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/Users/Movies", method = RequestMethod.POST)
	@ResponseBody
	public Boolean AddToFavorites(@RequestBody String MovieIdentifier) {
		UserServices users = new UserServices();
		User mockuser = new User();
		mockuser.UserId="1e027ad7-6420-48de-a8e3-ef0b0777b4d4";
		users.AddMovieToUserFavorites(mockuser, MovieIdentifier);
		return true;

	}
	
	
}
