package com.application.springwebflux.service.impl;


import com.application.springwebflux.model.Person;
import com.application.springwebflux.service.IPersonService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonServiceImpl implements IPersonService {
    private final List<Person> personList = new ArrayList<>();

    public PersonServiceImpl() {
        this.personList.add(new Person(1, 17, "张三"));
        this.personList.add(new Person(2, 18, "李四"));
    }

    @Override
    public Flux<Person> getList() {
        return Flux.fromIterable(personList);
    }

    @Override
    public Mono<Void> savePerson(Person person) {
        personList.add(person);
        System.out.println("personList.size = " + personList);
        return Mono.empty();
    }

    @Override
    public Mono<Person> getPerson(Integer id) {
        return Mono.justOrEmpty(personList.stream().filter(p -> p.getId().equals(id)).findFirst());
    }
}
