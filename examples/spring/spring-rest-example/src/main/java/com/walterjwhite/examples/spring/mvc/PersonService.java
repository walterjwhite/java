package com.walterjwhite.examples.spring.mvc;

import java.util.List;
import java.util.Optional;

public interface PersonService {
  List<Person> findAll();

  Optional<Person> findById(Long id);

  Person save(Person person);

  void deleteById(Long id);

  List<Person> search(String query);
}
