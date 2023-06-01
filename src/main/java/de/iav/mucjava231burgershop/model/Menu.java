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
        if (id == null){
            this.id = UUID.randomUUID().toString();
        } else {
            this.id = id;
        }
        this.name = name;
        this.price = price;
        this.mainDish = mainDish;
        this.sideDish = sideDish;
        this.beverage = beverage;
    }

    public Menu(String name, Double price, Dish mainDish, Dish sideDish, Beverage beverage)
    {
        this(UUID.randomUUID().toString(), name, price, mainDish, sideDish, beverage);
    }

}
