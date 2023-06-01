package de.iav.mucjava231burgershop.controller;

import de.iav.mucjava231burgershop.model.Beverage;
import de.iav.mucjava231burgershop.model.Dish;
import de.iav.mucjava231burgershop.model.Menu;
import de.iav.mucjava231burgershop.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class MenuControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MenuRepository menuRepository;

    @Test
    @DirtiesContext
    void listMenus_whenNoMenu_thenStatusOKAndReturnEmptyList() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/menus")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().json(
                        """
                            []
                        """
                ));

    }

    @Test
    @DirtiesContext
    void listMenus_whenMenuExists_thenStatusOKAndListAllMenus() throws Exception {

        this.menuRepository.addMenu(new Menu(
                "",
                "Menu 1",
                15.0,
                new Dish("Chicken wings"),
                new Dish("Potato chip"),
                new Beverage("Cola")));

        this.menuRepository.addMenu(new Menu(
                "",
                "Menu 2",
                18.0,
                new Dish("Beef burger"),
                new Dish("Potato chip"),
                new Beverage("Fanta")));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/menus"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Menu 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Menu 2"));

    }

    @Test
    @DirtiesContext
    void getMenuById_whenMenuExists_thenStatusOKAndReturnMenuWithGivenId() throws Exception {


        this.menuRepository.addMenu(new Menu(
                "Menu 4",
                20.0,
                new Dish("Beef burger"),
                new Dish("Potato chip"),
                new Beverage("Fanta")));

        final String searchID = this.menuRepository.list().get(0).id();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/menus/{id}", searchID)).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.jsonPath("$.id").value(searchID));

    }

    @Test
    @DirtiesContext
    void addMenu_whenAddMenu_thenTheMenuCanBeListed() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/menus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """ 
                            {
                                "id": "",
                                "name": "Menu 20", 
                                "price": 205.0,
                                "mainDish": {"name": "Beef burger"}, 
                                "sideDish": {"name": "Potato chip"}, 
                                "beverage": {"name": "Water"}
                            }
                        """
                )).andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/menus"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Menu 20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(205.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].mainDish.name").value("Beef burger"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sideDish.name").value("Potato chip"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].beverage.name").value("Water"));



    }

    @Test
    @DirtiesContext
    void updateMenu_whenUpdateMenu_thenMenuOfGivenIdShouldBeUpdated() throws Exception {

        this.menuRepository.addMenu(new Menu(
                "Menu 4",
                20.0,
                new Dish("Beef burger"),
                new Dish("Potato chip"),
                new Beverage("Fanta")));

        final String searchID = this.menuRepository.list().get(0).id();

        mockMvc.perform(MockMvcRequestBuilders.put("/api/menus/{id}", searchID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """ 
                            {
                                "name": "Menu 20", 
                                "price": 205.0,
                                "mainDish": {"name": "BBQ Meat"}, 
                                "sideDish": {"name": "Onion chip"}, 
                                "beverage": {"name": "Wine"}
                            }
                        """
                )).andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/menus/{id}", searchID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Menu 20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(205.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mainDish.name").value("BBQ Meat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sideDish.name").value("Onion chip"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.beverage.name").value("Wine"));

    }

    @Test
    void deleteMenu_whenDeleteExistingMenu_thenReturnListWithMenuDeleted() throws Exception {

        this.menuRepository.addMenu(new Menu(
                "Menu 4",
                20.0,
                new Dish("Beef burger"),
                new Dish("Potato chip"),
                new Beverage("Fanta")));

        final String searchID = this.menuRepository.list().get(0).id();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/menus/{id}", searchID))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/menus")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().json(
                        """
                            []
                        """
                ));

    }
}