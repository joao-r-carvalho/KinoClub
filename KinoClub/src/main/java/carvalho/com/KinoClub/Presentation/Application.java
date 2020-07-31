package carvalho.com.KinoClub.Presentation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import carvalho.com.KinoClub.Persistence.DBConnection;
import carvalho.com.KinoClub.Persistence.FilmPersistence;

@SpringBootApplication
public class Application {

	@Value("${spring.data.mongodb.uri}")
	private String MongoURI;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		DBConnection connection = DBConnection.GetInstance(MongoURI);
		return args -> {
			FilmPersistence persistence = new FilmPersistence();
			JSONObject object = persistence.GetFilmByUUID(UUID.fromString("0d366fed-be3e-44a8-a84c-9c0bdeafc238"));
			System.out.println(object.toString());
		};
	}

}