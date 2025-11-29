package ec.nttdata.person_user_ms.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class OpenApiConfig {

    static {
        // Configurar resolvers de esquema para tipos específicos
        SpringDocUtils.getConfig().replaceWithSchema(BigDecimal.class,
                new Schema<BigDecimal>()
                        .type("number")
                        .format("double")
                        .example(new BigDecimal("123.45"))
                        .description("Valor decimal de alta precisión"));
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CLIENT API")
                        .description("""
                                # 🏦 CLIENT API
                                client person api 
                                """)
                        .version("0.0.1")
                        .termsOfService("https://www.linkedin.com/in/christian-ruales-8bb769189/")
                        .contact(new Contact()
                                .name("Christian ruales")
                                .email("christianpaulruales@gmail.com")
                                .url("https://www.linkedin.com/in/christian-ruales-8bb769189/"))
                        .license(new License()
                                .name("APACHE 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8079")
                                .description("Servidor de Desarrollo"),
                        new Server()
                                .url("http://localhost:8079")
                                .description("Servidor de Producción")
                ));
    }
}