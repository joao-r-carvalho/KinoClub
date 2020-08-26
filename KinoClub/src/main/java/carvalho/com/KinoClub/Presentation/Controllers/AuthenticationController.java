package carvalho.com.KinoClub.Presentation.Controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import carvalho.com.KinoClub.Domain.Config.KinoConstants;
import carvalho.com.KinoClub.Domain.Models.Users.LoginRequest;
import carvalho.com.KinoClub.Domain.Models.Users.RegistrationRequest;
import carvalho.com.KinoClub.Domain.Models.Users.User;
import carvalho.com.KinoClub.Domain.Services.AuthenticationServices;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Authentication")
@Api(tags = "Authorization, authentication and user session control")
@CrossOrigin(origins = { "http://localhost:3000", "https://joao-r-carvalho.github.io","https://kinoclub-1595860726231.azurewebsites.net" }, allowCredentials = "true")
public class AuthenticationController extends BaseController {
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	@ResponseBody
	public User Login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		AuthenticationServices services = new AuthenticationServices();
		Cookie[] cooks = request.getCookies();
		String token = services.Login(loginRequest.Username, loginRequest.Password);

		if (token.isEmpty()) {
			response.sendError(401, "Wrong credentials");
			return null;
		} else {
			ResponseCookie cookie = services.BuildAuthenticationCookie(token);
			response.addHeader("Set-Cookie", cookie.toString());

			AuthenticationServices s = new AuthenticationServices();
			return s.GetUserFromToken(token);

		}

	}
	
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	@ResponseBody
	public void Logout( HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		AuthenticationServices services = new AuthenticationServices();
		Cookie[] cookies = request.getCookies();
		Optional<Cookie> Auth = Arrays.stream(cookies).filter(x -> x.getName().equalsIgnoreCase(KinoConstants.AuthenticationCookieName)).findFirst();
		if(Auth.isPresent()) {
			ResponseCookie cookie = services.DeleteAuthenticationCookie(Auth.get().getValue());
			response.addHeader("Set-Cookie", cookie.toString());
		}





	}
	

	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	@ResponseBody
	public String Register(@RequestBody RegistrationRequest registrationRequest, HttpServletResponse response) {
		AuthenticationServices services = new AuthenticationServices();
		User user = services.RegisterUser(registrationRequest);
		String token = services.Login(user.Username, registrationRequest.Password);
		ResponseCookie cookie = services.BuildAuthenticationCookie(token);
		response.addHeader("Set-Cookie", cookie.toString());
		return token;
	}

}
