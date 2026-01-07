package com.walterjwhite.examples.spring.bank;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankUserDetailsService implements UserDetailsService {
  private final UserRepository userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User u =
        userRepo
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    return new org.springframework.security.core.userdetails.User(
        u.getUsername(),
        u.getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority(u.getRole().name())));
  }
}
