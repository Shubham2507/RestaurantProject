
package com.infogain.api.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class TableBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)      
	private int tableId;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date bookingDate;
	private int noOfTables;
	private String guestName;
	@Column(unique=true)
	private String username;
	@Column(length = 10)
	private long mobileNo;
	private int isActive;
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	
	
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getNoOfTables() {
		return noOfTables;
	}
	public void setNoOfTables(int noOfTables) {
		this.noOfTables = noOfTables;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	public TableBooking(int tableId, Date bookingDate, int noOfTables, String guestName, String username, long mobileNo,
			int isActive) {
		super();
		this.tableId = tableId;
		this.bookingDate = bookingDate;
		this.noOfTables = noOfTables;
		this.guestName = guestName;
		this.username = username;
		this.mobileNo = mobileNo;
		this.isActive = isActive;
	}
	public TableBooking() {
		super();
	}

}
