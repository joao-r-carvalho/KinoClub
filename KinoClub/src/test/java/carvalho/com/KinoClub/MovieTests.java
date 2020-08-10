package carvalho.com.KinoClub;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Base64;

import org.json.JSONObject;

 import carvalho.com.KinoClub.Domain.Models.Movies.Movie;
import carvalho.com.KinoClub.Domain.Models.Users.RegistrationRequest;
import carvalho.com.KinoClub.Domain.Models.Users.User;
import carvalho.com.KinoClub.Domain.Services.AuthenticationServices;
import carvalho.com.KinoClub.Persistence.FilmPersistence;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

class MovieTests {


    @Test
    void ValidateHashing() throws Exception {
//    	AuthenticationServices s = new AuthenticationServices();
//		byte[] Salt = s.GenerateSalt();
//
//    	RegistrationRequest registrationRequest = new RegistrationRequest();
//		byte[] HashedSaltedPasswordBytes = s.HashAndSaltString(registrationRequest.Password, Salt);
//		User user = new User(registrationRequest);
//
//		String Base64EncodedPassword = new String(Base64.getEncoder().encode(HashedSaltedPasswordBytes));
//		String Base64EncodedSalt = new String(Base64.getEncoder().encode(Salt));
//
//		user.Base64EncodedSalt = Base64EncodedSalt;
//		user.Base64EncodedSaltedPasswordHash = Base64EncodedPassword;
		
		
		
		
		
    }


}