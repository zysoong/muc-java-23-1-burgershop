package de.iav.mucjava231burgershop.repository;

import de.iav.mucjava231burgershop.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Repository
public class MenuRepository {

    private final List<Menu> menus;

    public MenuRepository(List<Menu> menus){
        this.menus = menus;
    }

    public void addMenu(Menu menu){
        this.menus.add(menu);
    }

    public List<Menu> list(){
        return menus;
    }

    public Menu getMenuById(String id){
        for (Menu m : this.menus){
            if (m.id().equals(id)){
                return m;
            }
        }
        throw new NoSuchElementException("Menu with the id " + id + " was not found");
    }

    public void updateMenu(String id, Menu menu){
        for (int i = 0; i < this.menus.size(); i++){
            if (this.menus.get(i).id().equals(id)){
                this.menus.remove(i);
                Menu menuToAdd = new Menu(id, menu.name(), menu.price(), menu.mainDish(), menu.sideDish(), menu.beverage());
                this.menus.add(menuToAdd);
                return;
            }
        }
        throw new NoSuchElementException("Menu with the id " + id + " was not found");
    }

    public void deleteMenu(String id){
        for (int i = 0; i < this.menus.size(); i++){
            if (this.menus.get(i).id().equals(id)){
                this.menus.remove(i);
            }
        }
        throw new NoSuchElementException("Menu with the id " + id + " was not found");
    }



}
