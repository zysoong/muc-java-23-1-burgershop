package de.iav.mucjava231burgershop.service;

import de.iav.mucjava231burgershop.model.Menu;
import de.iav.mucjava231burgershop.repository.MenuRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MenuServiceTest {

    MenuRepository menuRepository = mock(MenuRepository.class);

    MenuService menuService = new MenuService(menuRepository);

    @Test
    void listMenus_whenNoMenuExists_ThenStatusOKAndReturnListWithLengthOfZero()
    {
        int expectedListLength = 0;
        when(menuRepository.list()).thenReturn(new ArrayList<Menu>());

        int actualListLength = menuService.listMenus().size();

        assertEquals(expectedListLength, actualListLength);
    }

    @Test
    void getMenuById() {
    }

    @Test
    void addMenu() {
    }

    @Test
    void updateMenu() {
    }

    @Test
    void deleteMenu() {
    }
}