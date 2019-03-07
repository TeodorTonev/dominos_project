package com.example.demo.product.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pasta extends Product{
    private long id;
    private String name;
    private String description;
    private double price;

    public Pasta() {
    }
}
