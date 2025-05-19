package com.das.foodesapi.io;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequest {

    private String name;
    private  String description;
    private double price;
    private String category;
}
