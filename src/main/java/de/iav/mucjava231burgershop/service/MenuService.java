package de.iav.mucjava231burgershop.service;

import de.iav.mucjava231burgershop.model.Menu;
import de.iav.mucjava231burgershop.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }

    public List<Menu> listMenus(){
        return this.menuRepository.list();
    }

    public Menu getMenuById(String id){
        return this.menuRepository.getMenuById(id);
    }

    public void addMenu(Menu menu){
        this.menuRepository.addMenu(menu);
    }

    public void updateMenu(String id, Menu menu){
        this.menuRepository.updateMenu(id, menu);
    }

    public void deleteMenu(String id){
        this.menuRepository.deleteMenu(id);
    }

}
