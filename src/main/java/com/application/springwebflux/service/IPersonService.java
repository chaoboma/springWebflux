package com.application.springwebflux.service;
import com.application.springwebflux.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonService {

    Flux<Person> getList();

    Mono<Void> savePerson(Person person);

    Mono<Person> getPerson(Integer id);
}
