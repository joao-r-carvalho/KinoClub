package carvalho.com.KinoClub.Presentation;

import java.util.Arrays;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import carvalho.com.KinoClub.Persistence.FilmPersistence;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			FilmPersistence persistence = new FilmPersistence();
			JSONObject object = persistence.GetFilmByUUID(UUID.fromString("0d366fed-be3e-44a8-a84c-9c0bdeafc238"));
			System.out.println(object.toString());
		};
	}

}