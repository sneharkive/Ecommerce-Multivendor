package com.roy.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Home {
  private List<HomeCategory> grid;

  private List<HomeCategory> electricCategories;

  private List<HomeCategory> shopByCategories;

  private List<HomeCategory> dealCategories;
  
  private List<Deal> deals;


}
