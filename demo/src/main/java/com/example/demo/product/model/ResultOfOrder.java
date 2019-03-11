package com.example.demo.product.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResultOfOrder {
    private int userId;
    private int restauranrId;
    private int addressId;
    private int quantity;
    private int productId;
    private LocalDate localDate;

    public ResultOfOrder() {

    }
}
