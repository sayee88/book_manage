package edu.kh.jdbc.member.vo;

import java.sql.Date;

public class Reserve {
	
	private int reserveNo;
	private int memberNo;
	private int bookNo;
	private String bookName;
	private char rentYn;
	private Date rentDate;
	private Date returnDate;
	private Date returnExpect;
	
	
	public Reserve() {
		// TODO Auto-generated constructor stub
	}


	public Reserve(int reserveNo, int memberNo, int bookNo, String bookName, char rentYn, Date rentDate,
			Date returnDate, Date returnExpect) {
		super();
		this.reserveNo = reserveNo;
		this.memberNo = memberNo;
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.rentYn = rentYn;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.returnExpect = returnExpect;
	}


	public int getReserveNo() {
		return reserveNo;
	}


	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public int getBookNo() {
		return bookNo;
	}


	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public char getRentYn() {
		return rentYn;
	}


	public void setRentYn(char rentYn) {
		this.rentYn = rentYn;
	}


	public Date getRentDate() {
		return rentDate;
	}


	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}


	public Date getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}


	public Date getReturnExpect() {
		return returnExpect;
	}


	public void setReturnExpect(Date returnExpect) {
		this.returnExpect = returnExpect;
	}


	@Override
	public String toString() {
		return "Reserve [reserveNo=" + reserveNo + ", memberNo=" + memberNo + ", bookNo=" + bookNo + ", bookName="
				+ bookName + ", rentYn=" + rentYn + ", rentDate=" + rentDate + ", returnDate=" + returnDate
				+ ", returnExpect=" + returnExpect + "]";
	}

	
	

	
	
	
	
	
	
	
	
}
