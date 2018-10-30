package com.infogain.api.service;

import java.util.List;

import org.boot.restaurant.api.dto.MenuDto;



public interface IMenuService {
	List<MenuDto> getAllMenu();

	String deleteOneMenu(int itemId);

	MenuDto findOneMenu(int itemId);

	MenuDto addItem(MenuDto menuDto);

	MenuDto updateMenu(MenuDto menuDto);

	String deleteAllMenu();

	MenuDto updateMenuURL(int itemId, MenuDto menuDto);
}
