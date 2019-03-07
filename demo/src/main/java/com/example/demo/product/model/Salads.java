package com.example.demo.product.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Salads extends Product{
    private long id;
    private String name;
    private String description;
    private double price;

    public Salads() {
    }
}
