package com.walterjwhite.examples.spring.bank;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepo;

  public User findUserByUsername(String username) {
    return userRepo.findByUsername(username).orElse(null);
  }

  public List<User> listAllUsers() {
    return userRepo.findAll();
  }

  public Optional<User> findUserById(Long id) {
    return userRepo.findById(id);
  }
}
