package com.walterjwhite.examples.spring.bank;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users")
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  @EqualsAndHashCode.Exclude
  private String username;

  @Column(nullable = false)
  @EqualsAndHashCode.Exclude
  private String password; // encoded

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @EqualsAndHashCode.Exclude
  private UserRole role = UserRole.USER;
}
