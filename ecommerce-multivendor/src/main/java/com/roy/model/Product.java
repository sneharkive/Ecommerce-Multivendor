package com.roy.model;

import java.time.LocalDateTime;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;

  private String description;

  private int mrpPrice;

  private int sellingPrice;

  private int discountPercent;
  
  private int quantity;

  private String color;

  @ElementCollection
  private List<String> images = new ArrayList<>();

  private int numRatings;

  @ManyToOne
  private Category category;

  @ManyToOne
  private Seller seller;

  private LocalDateTime createdAt;

  private String Sizes;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Review> reviews = new ArrayList<>();
}
