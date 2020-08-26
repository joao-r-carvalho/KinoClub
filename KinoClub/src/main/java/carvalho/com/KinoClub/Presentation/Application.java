package carvalho.com.KinoClub.Presentation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import carvalho.com.KinoClub.Domain.Config.AuthenticationInterceptor;
import carvalho.com.KinoClub.Persistence.DBConnection;
import carvalho.com.KinoClub.Persistence.FilmPersistence;
import io.swagger.models.HttpMethod;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
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
			System.out.println("Online");
		};
	}

	@Bean
	public AuthenticationInterceptor authenticationInterceptor() {
		AuthenticationInterceptor authenticationInterceptor = new AuthenticationInterceptor();
		return authenticationInterceptor;
	}

	@Configuration
	public class WebConfig implements WebMvcConfigurer {
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**");
			;
		}
	}

}