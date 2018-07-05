package test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@RequiredArgsConstructor
@SpringBootApplication
@EnableJpaRepositories
public class HelloWorldTestApplication {

	private final CarRepository repository;

	public static void main(String[] args) {

		SpringApplication.run(HelloWorldTestApplication.class, args);
	}


}
