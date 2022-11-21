package project.rico.darirumah.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {



    @Bean
    public OpenAPI springShopOpenAPI() {

        System.out.println("MAAASSUUUKKKK111111");
        // TODO: fix configuration
        return new OpenAPI()
                .info(new Info()
                        .title("dari-rumah")
                        .version("v1")
//                        .version(appProperties.getAPP_VERSION()+"- default")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Service Documentation")
                        .url("https://localhost"));
    }
}
