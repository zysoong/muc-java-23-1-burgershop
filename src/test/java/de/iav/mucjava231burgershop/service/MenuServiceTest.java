package de.iav.mucjava231burgershop.service;

import de.iav.mucjava231burgershop.model.Beverage;
import de.iav.mucjava231burgershop.model.Dish;
import de.iav.mucjava231burgershop.model.Menu;
import de.iav.mucjava231burgershop.repository.MenuRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuServiceTest {

    MenuRepository menuRepository = mock(MenuRepository.class);

    MenuService menuService = new MenuService(menuRepository);

    @Test
    void listMenus_whenNoMenuExists_ThenStatusOKAndReturnListWithLengthOfZero()
    {
        int expectedListLength = 0;
        when(menuRepository.list()).thenReturn(new ArrayList<Menu>());

        int actualListLength = menuService.listMenus().size();

        verify(menuRepository).list();
        assertEquals(expectedListLength, actualListLength);
    }

    @Test
    void listMenus_whenMenuExists_ThenStatusOKAndListAllMenus()
    {
        List<Menu> expectedMenus = new ArrayList<>();

        expectedMenus.add(new Menu(
                "M01",
                "Menu 1",
                15.0,
                new Dish("Chicken wings"),
                new Dish("Potato chip"),
                new Beverage("Cola")));

        expectedMenus.add(new Menu(
                "M02",
                "Menu 2",
                18.0,
                new Dish("Beef burger"),
                new Dish("Potato chip"),
                new Beverage("Fanta")));


        when(menuRepository.list()).thenReturn(expectedMenus);

        List<Menu> actualMenus = menuService.listMenus();

        verify(menuRepository).list();
        assertEquals(expectedMenus, actualMenus);
    }

    @Test
    void getMenuById_whenMenuExists_thenReturnMenuWithGivenId() {

        String expectedId = "M001";

        when(menuRepository.getMenuById(expectedId)).thenReturn(
                new Menu(
                        "M001",
                        "Menu 001",
                        18.0,
                        new Dish("Beef burger"),
                        new Dish("Potato chip"),
                        new Beverage("Fanta")));

        String actualId = menuRepository.getMenuById(expectedId).id();

        verify(menuRepository).getMenuById(expectedId);
        assertEquals(expectedId, actualId);

    }

    @Test
    void addMenu_whenMenuAdded_ThenVerifyAddMenuInMenuRepository() {

        Menu menuToBeAdded = new Menu(
                "M01",
                "Menu 1",
                15.0,
                new Dish("Chicken wings"),
                new Dish("Potato chip"),
                new Beverage("Cola"));

        menuService.addMenu(menuToBeAdded);

        verify(menuRepository).addMenu(any());

    }

    @Test
    void updateMenu_whenUpdateMenu_ThenVerifyUpdateMenuInMenuRepository() {

        String id = "M01";

        Menu originalMenu = new Menu(
                id,
                "Menu 1",
                15.0,
                new Dish("Chicken wings"),
                new Dish("Potato chip"),
                new Beverage("Cola"));

        Menu expectedMenu = new Menu(
            id,
            "Menu 2",
            25.0,
            new Dish("Burger"),
            new Dish("Potato chip"),
            new Beverage("Fanta"));

        menuService.addMenu(originalMenu);
        menuService.updateMenu(id, expectedMenu);

        verify(menuRepository).updateMenu(any(), any());
    }

    @Test
    void deleteMenu_whenDeleteMenu_ThenVerifyDeleteMenuInMenuRepository() {

        String id = "M01";

        Menu originalMenu = new Menu(
                id,
                "Menu 1",
                15.0,
                new Dish("Chicken wings"),
                new Dish("Potato chip"),
                new Beverage("Cola"));

        menuService.addMenu(originalMenu);
        menuService.deleteMenu(id);

        verify(menuRepository).deleteMenu(any());

    }
}