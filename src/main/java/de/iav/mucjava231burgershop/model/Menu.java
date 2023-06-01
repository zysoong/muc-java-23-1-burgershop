package de.iav.mucjava231burgershop.model;

public record Menu
        (String id,
         String name,
         Double price,
         Dish mainDish,
         Dish sideDish,
         Beverage beverage
         )
{

}
