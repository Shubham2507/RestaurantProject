package com.infogain.api.service;

public class Historyu {
int oId;
int totalPrice;
public int getoId() {
	return oId;
}
public void setoId(int oId) {
	this.oId = oId;
}
public int getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(int totalPrice) {
	this.totalPrice = totalPrice;
}
public Historyu(int oId, int totalPrice) {
	super();
	this.oId = oId;
	this.totalPrice = totalPrice;
}
public Historyu() {
	super();
}

}
