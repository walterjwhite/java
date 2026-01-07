package com.walterjwhite.examples.spring.bank;

import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> list() {
    return ResponseEntity.ok(userService.listAllUsers());
  }

  @GetMapping("/isAdmin")
  public ResponseEntity<Boolean> isAdmin(final Principal principal) {
    User user = userService.findUserByUsername(principal.getName());
    if (user == null) {
      ResponseEntity.notFound();
    }

    if (UserRole.ADMINISTRATOR.equals(user.getRole())) {
      return ResponseEntity.ok(true);
    }

    return ResponseEntity.status(401).body(false);
  }
}
