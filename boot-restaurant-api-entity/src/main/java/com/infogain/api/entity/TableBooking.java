
package com.infogain.api.entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class TableBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tableId;
  /*	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    @Temporal(TemporalType.DATE)*/
	
	private String bookingDate;
	
	/*@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm")*/
	
	private String time;
	private int noOfGuests;
    public int getNoOfGuests() {
		return noOfGuests;
	}
	public void setNoOfGuests(int noOfGuests) {
		this.noOfGuests = noOfGuests;
	}
	private int capacity;
	
	private String dateTime;
	
	/*@Column(unique=true)*/
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
	
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	
	public TableBooking(int tableId, String bookingDate, String time, int noOfGuests, int capacity, String dateTime,
			String username, long mobileNo, int isActive) {
		super();
		this.tableId = tableId;
		this.bookingDate = bookingDate;
		this.time = time;
		this.noOfGuests = noOfGuests;
		this.capacity = capacity;
		this.dateTime = dateTime;
		this.username = username;
		this.mobileNo = mobileNo;
		this.isActive = isActive;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public TableBooking() {
		super();
	}

}
