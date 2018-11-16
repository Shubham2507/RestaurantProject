package com.infogain.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Inventory")
public class InventoryForTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Inventory_id")
	private int inventoryId;
	@Column(name = "TableCapacity")
	private int tableCapacity;

	@Column(name = "NumberofTables")
	private int noOftables;
    	
	private int tableOccupied;
	
	private int atTen;
	private int atTwelve;
	private int atTwo;
	private int atFour;
	private int atSix;
	private int atEight;
	
	


	public int getAtTen() {
		return atTen;
	}

	public void setAtTen(int atTen) {
		this.atTen = atTen;
	}

	public int getAtTwelve() {
		return atTwelve;
	}

	public void setAtTwelve(int atTwelve) {
		this.atTwelve = atTwelve;
	}

	public int getAtTwo() {
		return atTwo;
	}

	public void setAtTwo(int atTwo) {
		this.atTwo = atTwo;
	}

	public int getAtFour() {
		return atFour;
	}

	public void setAtFour(int atFour) {
		this.atFour = atFour;
	}

	public int getAtSix() {
		return atSix;
	}

	public void setAtSix(int atSix) {
		this.atSix = atSix;
	}

	public int getAtEight() {
		return atEight;
	}

	public void setAtEight(int atEight) {
		this.atEight = atEight;
	}

	public int getTableOccupied() {
		return tableOccupied;
	}

	public void setTableOccupied(int tableOccupied) {
		this.tableOccupied = tableOccupied;
	}

	public int getTableCapacity() {
		return tableCapacity;
	}

	public void setTableCapacity(int tableCapacity) {
		this.tableCapacity = tableCapacity;
	}

	public int getNoOftables() {
		return noOftables;
	}

	public void setNoOftables(int noOftables) {
		this.noOftables = noOftables;
	}

	

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	




	

	public InventoryForTable(int inventoryId, int tableCapacity, int noOftables, int tableOccupied, int atTen,
			int atTwelve, int atTwo, int atFour, int atSix, int atEight) {
		super();
		this.inventoryId = inventoryId;
		this.tableCapacity = tableCapacity;
		this.noOftables = noOftables;
		this.tableOccupied = tableOccupied;
		this.atTen = atTen;
		this.atTwelve = atTwelve;
		this.atTwo = atTwo;
		this.atFour = atFour;
		this.atSix = atSix;
		this.atEight = atEight;
	}

	public InventoryForTable() {
		super();
	}
	
	
	
	
	
}
