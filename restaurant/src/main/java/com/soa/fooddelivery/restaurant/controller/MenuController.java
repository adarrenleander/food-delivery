package com.soa.fooddelivery.restaurant.controller;

import com.soa.fooddelivery.restaurant.dto.MenuDto;
import com.soa.fooddelivery.restaurant.dto.MenuItemDto;
import com.soa.fooddelivery.restaurant.dto.RestaurantDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuController {

    @PostMapping("/menu")
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto request){
        MenuDto menu = new MenuDto();
        menu.setId(request.getId());
        menu.setName(request.getName());
        menu.setRestaurant(new RestaurantDto("123","Restaurant Indonesia", "Indonesia"));
        menu.setActiveStatus(true);
        return ResponseEntity.ok().body(menu);
    }

    @PutMapping("/menu")
    public ResponseEntity<MenuDto> updateMenu(@RequestBody MenuDto request){
        MenuDto menu = new MenuDto();
        menu.setId(request.getId());
        menu.setName(request.getName());
        menu.setRestaurant(new RestaurantDto("123","Restaurant Indonesia", "Indonesia"));
        return ResponseEntity.ok().body(menu);
    }

    @GetMapping("/menu")
    public ResponseEntity<List<MenuDto>> getAllMenu(){
        List<MenuDto> response = getSampleFullMenu();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<MenuDto> getMenuById(@PathVariable(name = "id") String id){
        List<MenuDto> menus = getSampleFullMenu();
        MenuDto response = new MenuDto();
        for (MenuDto menuDto : menus){
            if (menuDto.getId().equals(id)){
                response = menuDto;
            }
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<MenuDto> deleteMenu(@PathVariable(name = "id") String id) {
        MenuDto response = new MenuDto();
        response.setId(id);
        response.setActiveStatus(false);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/menuItem")
    public ResponseEntity<MenuItemDto> createMenu(@RequestBody MenuItemDto request) {
        MenuItemDto response = new MenuItemDto();
        response.setId(request.getId());
        response.setName(request.getName());
        response.setPrice(request.getPrice());
        response.setDetail(request.getDetail());
        response.setMenu(new MenuDto("123","Burger Menu","Menu Burger", true));
        response.setActiveStatus(true);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/menuItem")
    public ResponseEntity<MenuItemDto> updateMenu(@RequestBody MenuItemDto request) {
        MenuItemDto response = new MenuItemDto();
        response.setId(request.getId());
        response.setName(request.getName());
        response.setPrice(request.getPrice());
        response.setDetail(request.getDetail());
        response.setMenu(new MenuDto("123","Burger Menu","Menu Burger", true));
        response.setActiveStatus(true);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/menuItem/{id}")
    public ResponseEntity<MenuItemDto> deleteMenuItem(@PathVariable(name = "id") String id) {
        MenuItemDto response = new MenuItemDto();
        response.setId(id);
        response.setActiveStatus(false);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/menuItem/{id}")
    public ResponseEntity<MenuItemDto> getMenuItemById(@PathVariable(name = "id") String id){
        List<MenuItemDto> menus = getSampleFullMenuItem();
        MenuItemDto response = new MenuItemDto();
        for (MenuItemDto menuDto : menus){
            if (menuDto.getId().equals(id)){
                response = menuDto;
            }
        }
        return ResponseEntity.ok().body(response);
    }

    public List<MenuDto> getSampleFullMenu() {
        List<MenuDto> response = new ArrayList<>();
        MenuDto menu1 = new MenuDto();
        menu1.setId("123");
        menu1.setName("Burger");
        menu1.setDetail("Burger Menu");
        menu1.setMenuItems(getSampleFullMenuItem());
        RestaurantDto restaurant1 = new RestaurantDto();
        restaurant1.setId("123");
        restaurant1.setName("name123");
        restaurant1.setAddress("Drienerlolaan 5, 7522 NB Enschede");
        menu1.setRestaurant(restaurant1);
        MenuDto menu2 = new MenuDto();
        menu2.setId("223");
        menu2.setName("Fries");
        menu2.setDetail("Potato Menu");
        menu2.setRestaurant(restaurant1);
        MenuDto menu3 = new MenuDto();
        menu3.setId("323");
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
        menu1.setId("123");
        menu1.setName("Burger");
        menu1.setDetail("Burger Menu");
        RestaurantDto restaurant1 = new RestaurantDto();
        restaurant1.setId("123");
        restaurant1.setName("name123");
        restaurant1.setAddress("Drienerlolaan 5, 7522 NB Enschede");
        menu1.setRestaurant(restaurant1);

        MenuItemDto menuItem1 = new MenuItemDto();
        menuItem1.setMenu(menu1);
        menuItem1.setPrice((float) 7.5);
        menuItem1.setName("Burger A");
        menuItem1.setDetail("Burger with chili sauce");
        menuItem1.setId("burgerA");
        MenuItemDto menuItem2 = new MenuItemDto();
        menuItem2.setMenu(menu1);
        menuItem2.setPrice((float) 7.5);
        menuItem2.setName("Burger B");
        menuItem2.setDetail("Burger with cheese");
        menuItem2.setId("burgerB");
        MenuItemDto menuItem3 = new MenuItemDto();
        menuItem3.setMenu(menu1);
        menuItem3.setPrice((float) 7.5);
        menuItem3.setName("Burger C");
        menuItem3.setDetail("Burger with chicken");
        menuItem3.setId("burgerC");
        response.add(menuItem1);
        response.add(menuItem2);
        response.add(menuItem3);
        return response;
    }
}
