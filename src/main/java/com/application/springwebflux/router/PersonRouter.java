package com.application.springwebflux.router;
import com.application.springwebflux.handler.PersonHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PersonRouter {
    @Resource
    private PersonHandler personHandler;
    @Bean
    public RouterFunction<ServerResponse> personRoutes() {
        return RouterFunctions.route()
                .GET("/person/{id}", RequestPredicates.accept(MediaType.APPLICATION_JSON), personHandler::getPerson)
                .GET("/person", RequestPredicates.accept(MediaType.APPLICATION_JSON), personHandler::listPeople)
                .POST("/person", personHandler::createPerson)
                .build();
    }
}
