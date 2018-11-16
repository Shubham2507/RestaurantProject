package com.infogain.api.service;

import java.util.List;

import com.infogain.api.entity.Cart;
import com.infogain.api.entity.TableBooking;

public interface ITableBookingService {
	List<TableBooking> getAllBookedTables();

	String deleteOneTableBooking(int tableId);


	TableBooking bookTable(TableBooking table);

	String updateTableBooking(TableBooking table);

	


}
