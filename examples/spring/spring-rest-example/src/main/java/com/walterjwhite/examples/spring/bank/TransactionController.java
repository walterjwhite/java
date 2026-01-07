package com.walterjwhite.examples.spring.bank;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
  private final TransactionService txService;
  private final UserService userService;

  @GetMapping("/user")
  public ResponseEntity<List<TransactionResponse>> list(Principal principal) {
    if (principal == null) {
      return ResponseEntity.status(401).build();
    }

    User user = userService.findUserByUsername(principal.getName());
    if (user == null) return ResponseEntity.status(401).build();
    List<TransactionResponse> list =
        txService.listForUser(user).stream()
            .map(
                t ->
                    new TransactionResponse(
                        t.getId(),
                        t.getDate(),
                        t.getDescription(),
                        t.getAmount(),
                        t.getStatus(),
                        t.getUser().getId()))
            .collect(Collectors.toList());
    return ResponseEntity.ok(list);
  }

  @GetMapping("/all")
  public ResponseEntity<List<TransactionResponse>> listAll() {
    List<TransactionResponse> resp =
        txService.listAll().stream()
            .map(
                t ->
                    new TransactionResponse(
                        t.getId(),
                        t.getDate(),
                        t.getDescription(),
                        t.getAmount(),
                        t.getStatus(),
                        t.getUser().getId()))
            .collect(Collectors.toList());
    return ResponseEntity.ok(resp);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<TransactionResponse>> listTransactionsForUser(
      @PathVariable("userId") Long userId) {
    List<TransactionResponse> resp =
        txService.listForUserId(userId).stream()
            .map(
                t ->
                    new TransactionResponse(
                        t.getId(),
                        t.getDate(),
                        t.getDescription(),
                        t.getAmount(),
                        t.getStatus(),
                        t.getUser().getId()))
            .collect(Collectors.toList());
    return ResponseEntity.ok(resp);
  }

  @PostMapping
  public ResponseEntity<?> create(Principal principal, @RequestBody TransactionRequest req) {
    return create(null, principal, req);
  }

  @PostMapping("/{userId}")
  public ResponseEntity<?> create(
      @PathVariable("userId") Long userId,
      Principal principal,
      @RequestBody TransactionRequest req) {
    if (principal == null) {
      return ResponseEntity.status(401).build();
    }
    if (req.getDate() == null || req.getDescription() == null || req.getAmount() == null) {
      return ResponseEntity.badRequest().body("date, description, and amount are required");
    }

    final User user = getUser(userId, principal);
    if (user == null) {
      return ResponseEntity.status(401).build();
    }

    Transaction tx = new Transaction();
    tx.setDate(req.getDate());
    tx.setDescription(req.getDescription());
    tx.setAmount(req.getAmount());
    if (UserRole.ADMINISTRATOR.equals(user.getRole())) {
      tx.setStatus(TransactionStatus.VERIFIED);
    }

    Transaction saved = txService.create(user, tx);
    TransactionResponse resp =
        new TransactionResponse(
            saved.getId(),
            saved.getDate(),
            saved.getDescription(),
            saved.getAmount(),
            saved.getStatus(),
            saved.getUser().getId());
    return ResponseEntity.ok(resp);
  }

  protected User getUser(final Long userId, final Principal principal) {
    return getUserOptional(userId)
        .orElseGet(() -> userService.findUserByUsername(principal.getName()));
  }

  protected Optional<User> getUserOptional(final Long userId) {
    if (userId != null) {
      return userService.findUserById(userId);
    }

    return Optional.empty();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(Principal principal, @PathVariable("id") Long id) {
    if (principal == null) return ResponseEntity.status(401).build();
    User user = userService.findUserByUsername(principal.getName());
    if (user == null) return ResponseEntity.status(401).build();
    try {
      boolean deleted = txService.delete(id, user);
      if (!deleted) return ResponseEntity.notFound().build();
      return ResponseEntity.noContent().build();
    } catch (SecurityException ex) {
      return ResponseEntity.status(403).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(
      @PathVariable("id") Long id, Principal principal, @RequestBody TransactionRequest req) {
    if (principal == null) {
      return ResponseEntity.status(401).build();
    }
    User caller = userService.findUserByUsername(principal.getName());
    if (caller == null) return ResponseEntity.status(401).build();

    try {
      Transaction incoming = new Transaction();
      incoming.setDate(req.getDate());
      incoming.setDescription(req.getDescription());
      incoming.setAmount(req.getAmount());

      Transaction updated;
      if (UserRole.ADMINISTRATOR.equals(caller.getRole())) {
        updated = txService.update(id, incoming);
      } else {
        updated = txService.update(id, incoming, caller);
      }

      return ResponseEntity.ok(
          new TransactionResponse(
              updated.getId(),
              updated.getDate(),
              updated.getDescription(),
              updated.getAmount(),
              updated.getStatus(),
              updated.getUser().getId()));
    } catch (SecurityException se) {
      return ResponseEntity.status(403).build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/approve/{id}")
  public ResponseEntity<?> approve(@PathVariable("id") Long id) {
    try {
      Transaction updated = txService.approve(id);
      return ResponseEntity.ok(
          new TransactionResponse(
              updated.getId(),
              updated.getDate(),
              updated.getDescription(),
              updated.getAmount(),
              updated.getStatus(),
              updated.getUser().getId()));
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/deny/{id}")
  public ResponseEntity<?> deny(@PathVariable("id") Long id) {
    try {
      Transaction updated = txService.deny(id);
      return ResponseEntity.ok(
          new TransactionResponse(
              updated.getId(),
              updated.getDate(),
              updated.getDescription(),
              updated.getAmount(),
              updated.getStatus(),
              updated.getUser().getId()));
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}
