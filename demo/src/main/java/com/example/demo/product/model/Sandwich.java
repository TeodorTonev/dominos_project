package com.example.demo.product.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sandwich extends Product{
    private long id;
    private String name;
    private String description;
    private double price;

    public Sandwich() {
    }
}
