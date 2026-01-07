package com.walterjwhite.examples.spring.mvc;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
  private final PersonRepository repository;

  @Value("${service.people.maxPersons:10}")
  private int maxPeople;


  @Override
  public List<Person> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Person> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  public synchronized Person save(Person person) {
    if (person.getId() == null) {
      long count = repository.count();
      if (count >= maxPeople) {
        throw new MaxCapacityExceededException(
            "Cannot add more people: maximum of " + maxPeople + " reached.");
      }
    }
    return repository.save(person);
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  @Override
  public List<Person> search(String query) {
    if (query == null || query.trim().isEmpty()) {
      return findAll();
    }
    String q = query.trim();
    return repository.findByNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(q, q);
  }
}
