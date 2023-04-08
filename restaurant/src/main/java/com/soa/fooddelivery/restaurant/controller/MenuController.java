package com.soa.fooddelivery.restaurant.controller;

import com.soa.fooddelivery.restaurant.dto.MenuDto;
import com.soa.fooddelivery.restaurant.dto.MenuItemDto;
import com.soa.fooddelivery.restaurant.dto.RestaurantDto;
import com.soa.fooddelivery.restaurant.entity.Menu;
import com.soa.fooddelivery.restaurant.repository.MenuItemRepository;
import com.soa.fooddelivery.restaurant.repository.MenuRepository;
import com.soa.fooddelivery.restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @PostMapping("/menu")
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto request){
        MenuDto response = menuService.createMenu(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/menu")
    public ResponseEntity<MenuDto> updateMenu(@RequestBody MenuDto request){
        MenuDto response = menuService.updateMenu(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/menu")
    public ResponseEntity<List<MenuDto>> getAllMenu(){
        List<MenuDto> response = menuRepository.findAllOrderByName();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<MenuDto> getMenuById(@PathVariable(name = "id") Integer id){
        MenuDto response = menuRepository.findDtoById(id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<MenuDto> deleteMenu(@PathVariable(name = "id") Integer id) {
        MenuDto response = menuService.deleteMenu(id);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/menuItem")
    public ResponseEntity<MenuItemDto> createMenuItem(@RequestBody MenuItemDto request) {
        MenuItemDto response = menuService.createMenuItem(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/menuItem")
    public ResponseEntity<MenuItemDto> updateMenu(@RequestBody MenuItemDto request) {
        MenuItemDto response = menuService.updateMenuItem(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/menuItem/{id}")
    public ResponseEntity<MenuItemDto> deleteMenuItem(@PathVariable(name = "id") Integer id) {
        MenuItemDto response = menuService.deleteMenuItem(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/menuItem/{id}")
    public ResponseEntity<MenuItemDto> getMenuItemById(@PathVariable(name = "id") Integer id){
        MenuItemDto response = menuItemRepository.findDtoById(id);
        return ResponseEntity.ok().body(response);
    }

   /* public List<MenuDto> getSampleFullMenu() {
        List<MenuDto> response = new ArrayList<>();
        MenuDto menu1 = new MenuDto();
        menu1.setId(123);
        menu1.setName("Burger");
        menu1.setDetail("Burger Menu");
        menu1.setMenuItems(getSampleFullMenuItem());
        RestaurantDto restaurant1 = new RestaurantDto();
        restaurant1.setId(123);
        restaurant1.setName("name123");
        restaurant1.setAddress("Drienerlolaan 5, 7522 NB Enschede");
        menu1.setRestaurant(restaurant1);
        MenuDto menu2 = new MenuDto();
        menu2.setId(223);
        menu2.setName("Fries");
        menu2.setDetail("Potato Menu");
        menu2.setRestaurant(restaurant1);
        MenuDto menu3 = new MenuDto();
        menu3.setId(323);
        menu3.setName("Chicken");
        menu3.setDetail("Chicken Menu");
        menu3.setRestaurant(restaurant1);
        response.add(menu1);
        response.add(menu2);
        response.add(menu3);
        return response;
    }

    public List<MenuItemDto> getSampleFullMenuItem() {
        List<MenuItemDto> response = new ArrayList<>();
        MenuDto menu1 = new MenuDto();
        menu1.setId(123);
        menu1.setName("Burger");
        menu1.setDetail("Burger Menu");
        RestaurantDto restaurant1 = new RestaurantDto();
        restaurant1.setId(123);
        restaurant1.setName("name123");
        restaurant1.setAddress("Drienerlolaan 5, 7522 NB Enschede");
        menu1.setRestaurant(restaurant1);

        MenuItemDto menuItem1 = new MenuItemDto();
        menuItem1.setMenu(menu1);
        menuItem1.setPrice((float) 7.5);
        menuItem1.setName("Burger A");
        menuItem1.setDetail("Burger with chili sauce");
        menuItem1.setId(1);
        MenuItemDto menuItem2 = new MenuItemDto();
        menuItem2.setMenu(menu1);
        menuItem2.setPrice((float) 7.5);
        menuItem2.setName("Burger B");
        menuItem2.setDetail("Burger with cheese");
        menuItem2.setId(2);
        MenuItemDto menuItem3 = new MenuItemDto();
        menuItem3.setMenu(menu1);
        menuItem3.setPrice((float) 7.5);
        menuItem3.setName("Burger C");
        menuItem3.setDetail("Burger with chicken");
        menuItem3.setId(3);
        response.add(menuItem1);
        response.add(menuItem2);
        response.add(menuItem3);
        return response;
    }

    */
}
