package ec.nttdata.transaction_account_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "ec.nttdata.transaction_account_ms.infrastructure.persistence.entities")
@EnableJpaRepositories(basePackages = "ec.nttdata.transaction_account_ms.infrastructure.persistence.jpa")
@SpringBootApplication
public class TransactionAccountMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionAccountMsApplication.class, args);
	}

}
