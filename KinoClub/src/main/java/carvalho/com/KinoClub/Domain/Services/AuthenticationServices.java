package carvalho.com.KinoClub.Domain.Services;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.Cookie;

import carvalho.com.KinoClub.Domain.Models.Users.RegistrationRequest;
import carvalho.com.KinoClub.Domain.Models.Users.User;
import carvalho.com.KinoClub.Persistence.UserPersistence;
import io.jsonwebtoken.Claims;

public class AuthenticationServices {
	public User RegisterUser(RegistrationRequest registrationRequest) {
		byte[] Salt = GenerateSalt();
		try {
			byte[] HashedSaltedPasswordBytes = HashAndSaltString(registrationRequest.Password, Salt);
			User user = new User(registrationRequest);

			String Base64EncodedPassword = new String(Base64.getEncoder().encode(HashedSaltedPasswordBytes));
			String Base64EncodedSalt = new String(Base64.getEncoder().encode(Salt));

			user.Base64EncodedSalt = Base64EncodedSalt;
			user.Base64EncodedSaltedPasswordHash = Base64EncodedPassword;
			// byte[] decoded = Base64.getDecoder().decode(encoded);
			UserPersistence.RegisterUser(user);
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public String Login(String username, String Password) {
		UserPersistence persistence = new UserPersistence();
		if (ValidatePassword(username, Password)) {
			User u = persistence.GetUser(username);
			return BuildAuthenticationToken(u.UserId);
		}
		return "";
	}

	public String BuildAuthenticationToken(String UserId) {
		SecurityServices s = new SecurityServices();
		String SessionIdentifier = UUID.randomUUID().toString();
		String Issuer = "KinoClub";
		String Subject = "Client";
		long ttl = 1000*60*60;

		HashMap<String,Object> claims = new HashMap<String,Object>();
		claims.put(s.Claim_UserID, UserId);
		
		
		String jwt = s.createJWT(SessionIdentifier, Issuer, Subject, ttl, claims);
		return jwt;
	}
	
	public User GetUserFromToken(String JWT) {
		SecurityServices securityServices = new SecurityServices();
		UserPersistence users = new UserPersistence();
		Claims JWTClaims = securityServices.decodeJWT(JWT);
		String UserId = (String) JWTClaims.get(securityServices.Claim_UserID);
		User user = users.GetUserByUserId(UserId);
		return user;
	}
	
	public Cookie BuildAuthenticationCookie(String token) {
		Cookie AuthenticationCookie = new Cookie("KCAuthentication" , token );
		AuthenticationCookie.setMaxAge(60*60*24);
		return AuthenticationCookie;
		
	}
	public Boolean ValidatePassword(String username, String password) {

		try {
			User user = UserPersistence.GetUser(username);

			byte[] CalculatedHash = HashAndSaltString(password, Base64.getDecoder().decode(user.Base64EncodedSalt));
			byte[] UserHash = Base64.getDecoder().decode(user.Base64EncodedSaltedPasswordHash);

			return Arrays.equals(UserHash, CalculatedHash);

		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;
		}

	}

	public Boolean DoesUsernameExist(String Username) {
		return UserPersistence.DoesUsernameExist(Username);

	}

	public byte[] HashAndSaltString(String StringToHash, byte[] SaltToAdd) throws Exception {

		KeySpec spec = new PBEKeySpec(StringToHash.toCharArray(), SaltToAdd, 65536, 128);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = factory.generateSecret(spec).getEncoded();
		return hash;

	}

	public byte[] GenerateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}

}
