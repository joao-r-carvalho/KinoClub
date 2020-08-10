package carvalho.com.KinoClub.Presentation.Controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import carvalho.com.KinoClub.Domain.Models.Users.LoginRequest;
import carvalho.com.KinoClub.Domain.Models.Users.RegistrationRequest;
import carvalho.com.KinoClub.Domain.Models.Users.User;
import carvalho.com.KinoClub.Domain.Services.AuthenticationServices;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Authentication")
@Api(tags = "Authorization, authentication and user session control")
public class AuthenticationController extends BaseController {
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	@ResponseBody
	public String Login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
		AuthenticationServices services = new AuthenticationServices();
		String token = services.Login(loginRequest.Username, loginRequest.Password);
		response.addCookie(services.BuildAuthenticationCookie(token));
		return token;

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	@ResponseBody
	public String Register(@RequestBody RegistrationRequest registrationRequest, HttpServletResponse response) {
		AuthenticationServices services = new AuthenticationServices();
		User user = services.RegisterUser(registrationRequest);
		String token = services.Login(user.Username,registrationRequest.Password);
		response.addCookie( services.BuildAuthenticationCookie(token) );

		return token;
	}

}
