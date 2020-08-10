package carvalho.com.KinoClub.Domain.Models.Users;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public class User {
	@Id
	public String UserId;
	public String Username;
	public String Name;
	public String Base64EncodedSaltedPasswordHash;
	public String Base64EncodedSalt;
	public User() {
		
	}
	public User(RegistrationRequest registrationRequest) {
		UserId = UUID.randomUUID().toString();
		Username = registrationRequest.Username;
		Name = registrationRequest.FullName;
		
	}
	
}
