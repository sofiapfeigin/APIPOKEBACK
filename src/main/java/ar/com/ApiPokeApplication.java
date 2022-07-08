package ar.com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiPokeApplication extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiPokeApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
