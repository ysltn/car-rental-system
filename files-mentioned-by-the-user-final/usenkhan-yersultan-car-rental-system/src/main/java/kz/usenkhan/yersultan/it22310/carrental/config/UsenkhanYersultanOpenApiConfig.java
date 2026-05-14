package kz.usenkhan.yersultan.it22310.carrental.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsenkhanYersultanOpenApiConfig {
    @Bean
    public OpenAPI openAPI(
            @Value("${app.student.fullName}") String studentName,
            @Value("${app.student.group}") String group
    ) {
        return new OpenAPI()
                .info(new Info()
                        .title("Car Rental System API")
                        .version("1.0.0")
                        .description("Student: " + studentName + " (" + group + ")"));
    }
}

