package com.roy.model;

import jakarta.validation.constraints.NotNull;

import com.roy.domain.AccountStatus;
import com.roy.domain.USER_ROLE;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seller {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String sellerName;

  private String mobile;

  @Column(nullable = false, unique = true)
  private String email;

  private String password;

  @Embedded
  private BusinessDetails businessDetails = new BusinessDetails();

  @Embedded
  private BankDetails bankDetails = new BankDetails();

  @OneToOne(cascade = CascadeType.ALL)
  private Address pickAddress = new Address();

  private String GSTIN;

  private USER_ROLE role = USER_ROLE.ROLE_SELLER;

  private boolean isEmailVerified = false;

  private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;

  
}
