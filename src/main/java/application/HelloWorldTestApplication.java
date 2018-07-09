package application;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.CarRepository;

@RequiredArgsConstructor
@SpringBootApplication
@EnableJpaRepositories
public class HelloWorldTestApplication {

	private final CarRepository repository;

	public static void main(String[] args) {

		SpringApplication.run(HelloWorldTestApplication.class, args);
	}


}
