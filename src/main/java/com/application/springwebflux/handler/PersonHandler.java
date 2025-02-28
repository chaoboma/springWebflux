package com.application.springwebflux.handler;
import com.application.springwebflux.model.Person;
import com.application.springwebflux.service.IPersonService;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class PersonHandler {

    @Resource
    private IPersonService personService;

    public Mono<ServerResponse> listPeople(ServerRequest request) {
        Flux<Person> people = personService.getList();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(people, Person.class);
    }


    public Mono<ServerResponse> createPerson(ServerRequest request) {
        return request.bodyToMono(Person.class)
                .flatMap(i -> personService.savePerson(i))
                .flatMap(p -> ServerResponse.ok().bodyValue(p));
    }

    public Mono<ServerResponse> getPerson(ServerRequest request) {
        int personId = Integer.parseInt(request.pathVariable("id"));
        return personService.getPerson(personId)
                .flatMap(person -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(person))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
