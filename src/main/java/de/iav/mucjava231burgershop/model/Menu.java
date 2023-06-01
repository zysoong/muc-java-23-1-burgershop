package de.iav.mucjava231burgershop.model;

public record Menu
        (String id, String name, double price,
         Dish mainDish,
         Dish sideDish,
         Beverage beverage
         )
{

}
