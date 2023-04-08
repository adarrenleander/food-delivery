package com.soa.fooddelivery.restaurant.service;

import com.soa.fooddelivery.restaurant.dto.MenuDto;
import com.soa.fooddelivery.restaurant.dto.MenuItemDto;
import com.soa.fooddelivery.restaurant.dto.RestaurantDto;
import com.soa.fooddelivery.restaurant.entity.Menu;
import com.soa.fooddelivery.restaurant.entity.MenuItem;
import com.soa.fooddelivery.restaurant.entity.Restaurant;
import com.soa.fooddelivery.restaurant.repository.MenuItemRepository;
import com.soa.fooddelivery.restaurant.repository.MenuRepository;
import com.soa.fooddelivery.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    RestaurantRepository restaurantRepository;


    @Transactional(rollbackFor={Exception.class})
    public MenuDto updateMenu (MenuDto menuDto){
        Menu menu = menuRepository.findAllById(menuDto.getId()).get(0);
        menu.setName(menuDto.getName());
        menu.setDetail(menuDto.getDetail());
        menuRepository.save(menu);
        return menu.convertToDto();
    }

    @Transactional(rollbackFor={Exception.class})
    public MenuDto createMenu (MenuDto menuDto){
        Menu menu = new Menu();
        menu.setName(menuDto.getName());
        menu.setDetail(menuDto.getDetail());
        menu.setActiveStatus(true);
        menu.setRestaurant(restaurantRepository.findAllById(menuDto.getRestaurant().getId()).get(0));
        menuRepository.save(menu);
        return menu.convertToDto();
    }

    @Transactional(rollbackFor={Exception.class})
    public MenuDto deleteMenu (Integer id){
        Menu menu = menuRepository.findAllById(id).get(0);
        menu.setActiveStatus(false);
        menuRepository.save(menu);
        return menu.convertToDto();
    }

    @Transactional(rollbackFor={Exception.class})
    public MenuItemDto createMenuItem (MenuItemDto menuItemDto){
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDto.getName());
        menuItem.setDetail(menuItemDto.getDetail());
        menuItem.setActiveStatus(true);
        menuItem.setMenu(menuRepository.findAllById(menuItemDto.getMenu().getId()).get(0));
        menuItemRepository.save(menuItem);
        return menuItem.convertToDto();
    }

    @Transactional(rollbackFor={Exception.class})
    public MenuItemDto updateMenuItem (MenuItemDto menuItemDto){
        MenuItem menuItem = menuItemRepository.findAllById(menuItemDto.getId()).get(0);
        menuItem.setName(menuItemDto.getName());
        menuItem.setPrice(menuItemDto.getPrice());
        menuItem.setDetail(menuItemDto.getDetail());
        menuItemRepository.save(menuItem);
        return menuItem.convertToDto();
    }

    @Transactional(rollbackFor={Exception.class})
    public MenuItemDto deleteMenuItem (Integer id){
        MenuItem menuItem = menuItemRepository.findAllById(id).get(0);
        menuItem.setActiveStatus(false);
        menuItemRepository.save(menuItem);
        return menuItem.convertToDto();
    }


}
