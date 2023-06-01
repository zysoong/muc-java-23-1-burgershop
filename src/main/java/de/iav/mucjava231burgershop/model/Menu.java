package de.iav.mucjava231burgershop.model;

import java.util.UUID;

public record Menu
        (String id,
         String name,
         Double price,
         Dish mainDish,
         Dish sideDish,
         Beverage beverage
         )
{



    public Menu(String id, String name, Double price, Dish mainDish, Dish sideDish, Beverage beverage)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.mainDish = mainDish;
        this.sideDish = sideDish;
        this.beverage = beverage;
    }

}
