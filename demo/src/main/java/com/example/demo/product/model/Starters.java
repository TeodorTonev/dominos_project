package com.example.demo.product.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Starters extends Product{
    private long id;
    private String name;
    private String description;
    private double price;

    public Starters() {
    }
}
