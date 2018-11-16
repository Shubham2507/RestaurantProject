package com.infogain.api.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infogain.api.entity.TableBooking;

@Repository
public interface TableBookingRepository extends JpaRepository<TableBooking, Integer> {
	
	 @Query(value = "SELECT COUNT(*) FROM Table_Booking WHERE booking_date = ?1 and capacity=?2" , nativeQuery = true)
	  int findBybookingDateandcapacity(String dateTime,int capacity);


}
