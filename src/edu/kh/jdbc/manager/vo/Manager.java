package edu.kh.jdbc.manager.vo;

import java.sql.Date;


public class Manager {
	
	private int bookNo;
	private String bookName;
	private String bookWriter;
	private String bookGr;
	private Date enrollDate;
	private int bookRcount;
	private char rentYn;
	
	private int memberNo;
	private String memberId;
	private String memberName;
	private String phone;
	private String address;
	private char secessionFlag;
	
	private int revNo;
	private Date rentDate;
	private Date returnDate;
	
	private String managerId;
	private String managerPw;
	
	public Manager() { }

	public Manager(int bookNo, String bookName, String bookWriter, String bookGr, Date enrollDate, char rentYn,
			int memberNo, String memberId, String memberName, String phone, String address, char secessionFlag,
			int revNo, Date rentDate, Date returnDate, String managerId, String managerPw) {
		super();
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookGr = bookGr;
		this.enrollDate = enrollDate;
		this.rentYn = rentYn;
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.phone = phone;
		this.address = address;
		this.secessionFlag = secessionFlag;
		this.revNo = revNo;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.managerId = managerId;
		this.managerPw = managerPw;
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

	public String getBookWriter() {
		return bookWriter;
	}

	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}

	public String getBookGr() {
		return bookGr;
	}

	public void setBookGr(String bookGr) {
		this.bookGr = bookGr;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public int getBookRcount() {
		return bookRcount;
	}

	public void setBookRcount(int bookRcount) {
		this.bookRcount = bookRcount;
	}

	public char getRentYn() {
		return rentYn;
	}

	public void setRentYn(char rentYn) {
		this.rentYn = rentYn;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public char getSecessionFlag() {
		return secessionFlag;
	}

	public void setSecessionFlag(char secessionFlag) {
		this.secessionFlag = secessionFlag;
	}

	public int getRevNo() {
		return revNo;
	}

	public void setRevNo(int revNo) {
		this.revNo = revNo;
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

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerPw() {
		return managerPw;
	}

	public void setManagerPw(String managerPw) {
		this.managerPw = managerPw;
	}

	@Override
	public String toString() {
		return "Manager [bookNo=" + bookNo + ", bookName=" + bookName + ", bookWriter=" + bookWriter + ", bookGr="
				+ bookGr + ", enrollDate=" + enrollDate + ", bookRcount=" + bookRcount + ", rentYn=" + rentYn
				+ ", memberNo=" + memberNo + ", memberId=" + memberId + ", memberName=" + memberName + ", phone="
				+ phone + ", address=" + address + ", secessionFlag=" + secessionFlag + ", revNo=" + revNo
				+ ", rentDate=" + rentDate + ", returnDate=" + returnDate + ", managerId=" + managerId + ", managerPw="
				+ managerPw + "]";
	}

	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
