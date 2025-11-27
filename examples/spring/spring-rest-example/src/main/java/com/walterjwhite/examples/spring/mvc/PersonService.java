package com.walterjwhite.examples.spring.mvc;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {
    private final ConcurrentHashMap<Long, Person> storage = new ConcurrentHashMap<>();
    private final AtomicLong idSeq = new AtomicLong(1);

    @Value("${service.people.maxPersons:10}")
    private int maxPeople;

    @PostConstruct
    protected void init() {
        save(new Person(null, "Alice", "alice@example.org"));
        save(new Person(null, "Bob", "bob@example.org"));
    }

    public List<Person> findAll() {
        ArrayList<Person> list = new ArrayList<>(storage.values());
        list.sort(Comparator.comparing(Person::getId));
        return list;
    }

    public Optional<Person> findById(@Name("id")Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public synchronized Person save(Person p) {
        if (p.getId() == null) {
            if (storage.size() >= maxPeople) {
                throw new MaxCapacityExceededException("Cannot add more people: maximum of " + maxPeople + " reached.");
            }
            p.setId(idSeq.getAndIncrement());
        }
        storage.put(p.getId(), p);
        return p;
    }

    public void deleteById(@Name("id") Long id) {
        storage.remove(id);
    }
}