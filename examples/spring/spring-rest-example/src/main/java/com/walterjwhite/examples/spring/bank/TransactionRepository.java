package com.walterjwhite.examples.spring.bank;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findAllByUserOrderByDateDesc(User user);

  List<Transaction> findAllByUserIdOrderByDateDesc(Long userId);

  List<Transaction> findAllByStatusOrderByDateDesc(TransactionStatus status);
}
