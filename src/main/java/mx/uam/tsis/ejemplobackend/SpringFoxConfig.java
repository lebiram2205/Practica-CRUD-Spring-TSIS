package mx.uam.tsis.ejemplobackend;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SpringFoxConfig {

    /**
     * Publish a bean to generate swagger2 endpoints
     * @return a swagger configuration bean
     */
    @Bean
    public Docket usersApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

   private ApiInfo apiInfo() {
        return new ApiInfo(
        	"Mi primera API REST",
        	"Ejemplo de API DE curso TSIS",
        	"API TOS",
        	"Dems of service",
        	new Contact("Maribel Contreras","www.humberto.net", "maribelcontrerasvidal@gmail.com"),
        	"Licence of API","API LICENCE URL", Collections.emptyList());
        }

    }

