package com.roy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.EqualsAndHashCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class SellerReport {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne
  private Seller seller;

  private Long totalEarning = 0L;

  private Long totalSales = 0L;

  private Long totalRefunds = 0L;

  private Long totalTax = 0L;

  private Long netEarning= 0L;

  private Integer totalOrders = 0;

  private Integer canceledOrders = 0;

  private Integer totalTransactions = 0;


}
