

package com.infogain.api.service;

import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.boot.restaurant.api.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.api.entity.Menu;
import com.infogain.api.repo.MenuRepository;



@Service("menuService")
public class MenuServiceImpl implements IMenuService {
	@Autowired
	private MenuRepository menuRepo;

	@Override
	public List<MenuDto> getAllMenu() {
		List<Menu> menuList = menuRepo.findAll();
		return menuAssembler(menuList);
	}

	// ASSEMBLER
	private List<MenuDto> menuAssembler(List<Menu> menuList) {
		List<MenuDto> menuDto = new ArrayList<>();
		menuList.forEach(menu -> {
			MenuDto sdto = new MenuDto();
			sdto.setItemId(menu.getItemId());
			sdto.setItemName(menu.getItemName());
			sdto.setCategory(menu.getCategory());
			sdto.setDescription(menu.getDescription());
			sdto.setQuantity(menu.getQuantity());
			sdto.setRate(menu.getRate());
			menuDto.add(sdto);
		});
		return menuDto;
	}

	@Override
	public String deleteOneMenu(int itemId) {
		menuRepo.delete(menuRepo.getOne(itemId));
		return "Deletion Successful";
	}

	@Override
	public MenuDto findOneMenu(int itemId) {
		Menu men = menuRepo.getOne(itemId);
		MenuDto menuDto = new MenuDto();
		menuDto.setItemId(men.getItemId());
		menuDto.setItemName(men.getItemName());
		menuDto.setDescription(men.getDescription());
		menuDto.setCategory(men.getCategory());
		menuDto.setQuantity(men.getQuantity());
		menuDto.setRate(men.getRate());

		return menuDto;
	}

	@Override
	public MenuDto addItem(MenuDto menuDto) {
		Menu menu = new Menu();

		menu.setItem_Name(menuDto.getItemName());
		menu.setDescription(menuDto.getDescription());
		menu.setCategory(menuDto.getCategory());
		menu.setQuantity(menuDto.getQuantity());
		menu.setRate(menuDto.getRate());

		menuRepo.save(menu);
		return menuDto;
	}

	@Override
	public MenuDto updateMenu(MenuDto menuDto) {
		//
		return null;
	}

	@Override
	public String deleteAllMenu() {
		menuRepo.deleteAll();
		return "Deletion Successful";

	}

	@Override
	@Transactional
	public MenuDto updateMenuURL(int itemId, MenuDto menuDto) {
		Menu menu = menuRepo.getOne(itemId);
		menu.setRate(menuDto.getRate());

		return menuDto;
	}

}
