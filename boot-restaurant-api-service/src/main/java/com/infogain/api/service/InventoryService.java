package com.infogain.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infogain.api.entity.InventoryForTable;

import com.infogain.api.repository.TableInventoryRepo;

@EnableWebMvc
@Service("iService")
public class InventoryService {
	@Autowired
	private TableInventoryRepo inventRepo;

	public List<InventoryForTable> getAllTableInventory() {
		return inventRepo.findAll();

	}

	public InventoryForTable addInventory(InventoryForTable table) {

		InventoryForTable inventoryForTable = new InventoryForTable();
		inventoryForTable.setTableCapacity(table.getTableCapacity());
		inventoryForTable.setNoOftables(table.getNoOftables());

		inventoryForTable.setTableOccupied(0);
		inventRepo.save(inventoryForTable);

		return inventoryForTable;
	}

	public String updateInventory(InventoryForTable inventory) {
		InventoryForTable tempInventory = inventRepo.getInventoryByinventoryId(inventory.getInventoryId());
		tempInventory.setNoOftables(inventory.getNoOftables());
		tempInventory.setTableOccupied(inventory.getTableOccupied());
		tempInventory.setAtTen(inventory.getAtTen());
		tempInventory.setAtTwelve(inventory.getAtTwelve());
		tempInventory.setAtTwo(inventory.getAtTwo());
		tempInventory.setAtFour(inventory.getAtFour());
		tempInventory.setAtSix(inventory.getAtSix());
		tempInventory.setAtEight(inventory.getAtEight());
		inventRepo.save(tempInventory);
		return "Update Successfully!";
	}

}
