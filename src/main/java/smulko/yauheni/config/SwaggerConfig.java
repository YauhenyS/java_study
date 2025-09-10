package smulko.yauheni.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI blogOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog API")
                        .description("Backend API для блога с пользователями, постами, тегами, комментариями и аудитом")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Yauheni Smulko")
                                .email("yauheny.smulko@gmail.com")));
    }
}
