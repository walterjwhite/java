package com.walterjwhite.examples.spring.bank;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
  private final TransactionRepository txRepo;

  public List<Transaction> listForUser(User user) {
    return txRepo.findAllByUserOrderByDateDesc(user);
  }

  public List<Transaction> listForUserId(Long userId) {
    return txRepo.findAllByUserIdOrderByDateDesc(userId);
  }

  public List<Transaction> listAll() {
    return txRepo.findAll();
  }

  public Optional<Transaction> findById(Long id) {
    return txRepo.findById(id);
  }

  public Transaction create(User user, Transaction tx) {
    tx.setUser(user);
    if (UserRole.ADMINISTRATOR.equals(user.getRole())) {
      tx.setStatus(TransactionStatus.VERIFIED);
    } else {
      tx.setStatus(TransactionStatus.NEW);
    }
    return txRepo.save(tx);
  }

  public Transaction update(Long id, Transaction incoming) {
    Transaction existing = txRepo.findById(id).orElseThrow();
    existing.setDate(incoming.getDate());
    existing.setDescription(incoming.getDescription());
    existing.setAmount(incoming.getAmount());
    existing.setStatus(TransactionStatus.VERIFIED);
    return txRepo.save(existing);
  }

  public Transaction update(Long id, Transaction incoming, User user) {
    Transaction existing = txRepo.findById(id).orElseThrow();
    if (!existing.getUser().getId().equals(user.getId())) {
      throw new SecurityException("Not owner");
    }

    existing.setDate(incoming.getDate());
    existing.setDescription(incoming.getDescription());
    existing.setAmount(incoming.getAmount());
    existing.setStatus(TransactionStatus.NEW); // mark pending for admin review
    return txRepo.save(existing);
  }

  public Transaction approve(Long id) {
    Transaction t = txRepo.findById(id).orElseThrow();
    t.setStatus(TransactionStatus.VERIFIED);
    return txRepo.save(t);
  }

  public Transaction deny(Long id) {
    Transaction t = txRepo.findById(id).orElseThrow();

    if (t.getStatus().equals(TransactionStatus.PENDING_DELETION)) {
      t.setStatus(TransactionStatus.DENIED_DELETION);
    } else {
      t.setStatus(TransactionStatus.DENIED);
    }

    return txRepo.save(t);
  }

  public boolean delete(final Long id, User user) {
    Optional<Transaction> maybe = txRepo.findById(id);
    if (maybe.isEmpty()) return false;
    Transaction tx = maybe.get();
    if (UserRole.ADMINISTRATOR.equals(user.getRole())) {
      tx.setStatus(TransactionStatus.DELETED);
      txRepo.save(tx);
      return true;
    }

    if (!tx.getUser().getId().equals(user.getId())) {
      throw new SecurityException("Not owner");
    }

    tx.setStatus(TransactionStatus.PENDING_DELETION);
    txRepo.save(tx);
    return true;
  }
}
