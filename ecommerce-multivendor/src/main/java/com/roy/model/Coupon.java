package com.roy.model;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coupon {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String code;

  private double discountPercentage;

  private LocalDate validityStartDate;

  private LocalDate validityEndDate;

  private double minOrderValue;

  private boolean isActive = true;

  @ManyToMany(mappedBy = "usedCoupons")
  private Set<User> usedByUsers = new HashSet<>();

}
