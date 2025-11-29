package ec.nttdata.person_user_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "ec.nttdata.person_user_ms.infrastructure.persistence.entities")
@EnableJpaRepositories(basePackages = "ec.nttdata.person_user_ms.infrastructure.persistence.jpa")
@SpringBootApplication
public class PersonUserMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonUserMsApplication.class, args);
	}

}
