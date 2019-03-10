package com.example.demo.product.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResultOfRequest {

    private String firstName;
    private String lastName;
    private String city;
    private String neighborhood;
    private String productName;
    private int quantity;
    private String date;

    public ResultOfRequest() {}
}
