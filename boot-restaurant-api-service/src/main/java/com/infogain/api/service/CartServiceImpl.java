package com.infogain.api.service;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.api.entity.Cart;
import com.infogain.api.entity.Menu;
import com.infogain.api.repo.CartRepository;


@Service("cartService")
public class CartServiceImpl implements ICartService {
	@Autowired
	private CartRepository cartRepo;

	@Override
	public List<Cart> getAllCart() {
		List<Cart> cart = cartRepo.findAll();
		return cart;
	}

	// ASSEMBLER
	/*
	 * private List<Cart> deptAssembler(List<Cart> cart) { List<Cart> c = new
	 * ArrayList<>(); cart.forEach(carts -> { CartDto cartDto = new CartDto();
	 * cartDto.setCartId(carts.getCartId()); cartDto.setTotalPrice(carts.ge);
	 * cartDto.setUsername(carts.getUsername());
	 * cartDto.setItemId(carts.getItemId());
	 * cartDto.setItemName(carts.getItemName());
	 * cartDto.setCategory(carts.getCategory());
	 * cartDto.setDescription(carts.getDescription());
	 * cartDto.setQuantity(carts.getQuantity()); cartDto.setRate(carts.getRate());
	 * 
	 * 
	 * cdto.add(cartDto);
	 * 
	 * }); return cdto; }
	 */
	@Override
	public String deleteOneCart(int cartId) {
		cartRepo.delete(cartRepo.getOne(cartId));
		return "Deletion Successful";
	}

	/*
	 * @Override public CartDto findOneCart(int cartId) { Cart cart =
	 * cartRepo.getOne(cartId); CartDto cartDto = new CartDto();
	 * 
	 * cartDto.setCartId(cart.getCartId());
	 * cartDto.setTotalPrice(cart.getTotalPrice());
	 * 
	 * Set<MenuDto> sDto = entToDto(cart.getmenu()); cartDto.setMenu(sDto);
	 * 
	 * return cartDto; }
	 */// ENTITY TO DTO
	/*
	 * public Set<MenuDto> entToDto(Set<Menu> menu) {
	 * 
	 * Set<MenuDto> sDto = new HashSet<MenuDto>(); menu.forEach(mSet -> { MenuDto
	 * mDto = new MenuDto(); mDto.setItemId(mSet.getItemId());
	 * mDto.setItemName(mSet.getItemName());
	 * mDto.setDescription(mSet.getDescription());
	 * mDto.setCategory(mSet.getCategory()); mDto.setQuantity(mSet.getQuantity());
	 * mDto.setRate(mSet.getRate()); sDto.add(mDto); }); return sDto; }
	 */
	@Override
	public Cart addNewCart(Cart newCart) {
		Cart cart = new Cart();
		cart.setUsername(newCart.getUsername());
		cart.setItemId(newCart.getItemId());
		cart.setItemName(newCart.getItemName());
		cart.setDescription(newCart.getDescription());
		cart.setCategory(newCart.getCategory());
		cart.setRate(newCart.getRate());
		cart.setQuantity(newCart.getQuantity());
		cart.setTotalPrice(newCart.getQuantity() * newCart.getRate());

		cartRepo.save(cart);
		return newCart;
	}

	// DTO TO ENTITY
	/*
	 * private Set<Menu> empEntity(Set<Menu> menu) { Set<Menu> menuSet = new
	 * HashSet<>(); menu.forEach(menSet -> { Menu men = new Menu();
	 * men.setItem_Id(menSet.getItemId()); men.setItem_Name(menSet.getItemName());
	 * men.setDescription(menSet.getDescription());
	 * men.setCategory(menSet.getCategory()); men.setQuantity(menSet.getQuantity());
	 * men.setRate(menSet.getRate()); menuSet.add(men); }); return menuSet;
	 * 
	 * }
	 */
	@Override
	public void deleteAllCart() {
		cartRepo.deleteAll();

	}

	

	@Override
	public Cart updateCart(int cartId,Cart cart) {
		Cart newCart = cartRepo.getOne(cartId);
		newCart.setQuantity(cart.getQuantity());
		newCart.setTotalPrice(cart.getQuantity()*cart.getRate());
		return cartRepo.save(newCart);
		 

		
	}

}
