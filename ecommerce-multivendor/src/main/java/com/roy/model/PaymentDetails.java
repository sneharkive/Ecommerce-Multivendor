package com.roy.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.roy.domain.PaymentStatus;

import lombok.EqualsAndHashCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentDetails {

  private String paymentId;
  private String razorpayPaymentLinkId;
  private String razorpayPaymentLinkReferenceId;
  private String razorpayPaymentLinkStatus;
  private String razorpayPaymentId;
  private PaymentStatus status;

}
