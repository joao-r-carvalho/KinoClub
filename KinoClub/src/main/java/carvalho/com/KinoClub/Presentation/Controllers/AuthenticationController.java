package carvalho.com.KinoClub.Presentation.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Authentication")
@Api(tags = "Authorization, authentication and user session control")
public class AuthenticationController extends BaseController{
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	@ResponseBody
	public String Login() {
		return "MockJWT";		
	}

}
