package com.roy.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.EqualsAndHashCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Wishlist {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @OneToOne
  private User user;

  @ManyToMany
  private Set<Product> products = new HashSet<>();

}
