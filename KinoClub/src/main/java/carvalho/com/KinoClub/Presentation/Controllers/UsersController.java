package carvalho.com.KinoClub.Presentation.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/Users")
@Api(tags="User preferences" )
public class UsersController extends BaseController {
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/Me", method = RequestMethod.GET)
	@ResponseBody
	public String Profile() {
		return "Zé Manel";		
	}
}
