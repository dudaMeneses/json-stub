package nl.rabobank.powerofattorney.stub.config;

import com.fasterxml.classmate.TypeResolver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.util.Set;

@Configuration
@EnableSwagger2WebFlux
@RequiredArgsConstructor
public class SwaggerConfig {

    @NonNull
    private TypeResolver resolver;

    @Bean
    public Docket diffApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder().title("Power of Attorney API").build())
                .ignoredParameterTypes(getModelClasses())
                .tags(
                        new Tag("accounts", "Services related with accounts"),
                        new Tag("cards", "Services related with cards"),
                        new Tag("power-of-attorneys", "Services related with user grants")

                );
    }

    private Class[] getModelClasses() {
        Reflections reflections = new Reflections("nl.rabobank.powerofattorney.stub.model.entity");
        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);
        return allClasses.toArray(Class[]::new);
    }
}
