package project.rico.darirumah.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${spring.profiles.active:}")
    private String activeProfiles;

    @Autowired
    private AppProperties appProperties;

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(appProperties.getAPP_NAME())
                        .version(activeProfiles)
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Doc " + appProperties.getAPP_NAME())
                        .url("http://localhost"));

    }
}
