package com.walterjwhite.examples.spring.bank;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserRepository userRepo;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
    if (req.getUsername() == null || req.getPassword() == null) {
      return ResponseEntity.badRequest().body("username and password required");
    }
    if (userRepo.existsByUsername(req.getUsername())) {
      return ResponseEntity.badRequest().body("username already exists");
    }
    User u = new User();
    u.setUsername(req.getUsername());
    u.setPassword(passwordEncoder.encode(req.getPassword()));
    u.setRole(UserRole.USER);
    userRepo.save(u);
    return ResponseEntity.ok().build();
  }
}
