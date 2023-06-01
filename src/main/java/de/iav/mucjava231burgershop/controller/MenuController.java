package de.iav.mucjava231burgershop.controller;

import de.iav.mucjava231burgershop.model.Menu;
import de.iav.mucjava231burgershop.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping
    public List<Menu> listMenus(){
        return this.menuService.listMenus();
    }

    @GetMapping("/{id}")
    public Menu getMenuById(@PathVariable String id)
    {
        return this.menuService.getMenuById(id);
    }

    @PostMapping
    public void addMenu(@RequestBody  Menu menu)
    {
        this.menuService.addMenu(menu);
    }

    @PutMapping("/{id}")
    public void updateMenu(@PathVariable String id, @RequestBody Menu menu){
        this.menuService.updateMenu(id, menu);
    }

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable String id){
        this.menuService.deleteMenu(id);
    }


}
