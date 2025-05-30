package com.roy.model;

import java.util.*;

import com.roy.domain.PaymentMethod;
import com.roy.domain.PaymentOrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.EqualsAndHashCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class PaymentOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long amount;

  private PaymentOrderStatus status = PaymentOrderStatus.PENDING;

  private PaymentMethod paymentMethod;

  private String paymentLinkId;

  @ManyToOne
  private User user;

  @OneToMany
  private Set<Order> orders = new HashSet<>();

}
