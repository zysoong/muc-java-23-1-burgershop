package de.iav.mucjava231burgershop.controller;

import de.iav.mucjava231burgershop.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MenuRepository menuRepository;

    @Test
    void listMenus_whenNoMenu_thenStatus() {
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