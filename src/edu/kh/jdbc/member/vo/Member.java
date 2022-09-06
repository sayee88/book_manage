package edu.kh.jdbc.member.vo;

import java.sql.Date;

public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String phone;
	private String address;
	private char secessionFlag;
	
	private int bookNo;
	private String bookName;
	private String bookWriter;
	private String bookGr;
	private Date enrollDate;
	private int bookRcount;
	private char rentYn;
	
	private int rank;
	
	public Member(int rank) {
		super();
		this.rank = rank;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	private int reserveNo;
	private Date rentDate;
	private Date returnExpect;
	
	
	public Member() {
		// TODO Auto-generated constructor stub
	}


	public Member(int memberNo, String memberId, String memberPw, String memberName, String phone, String address,
			char secessionFlag, int bookNo, String bookName, String bookWriter, String bookGr, Date enrollDate,
			int bookRcount, char rentYn, int reserveNo, Date rentDate, Date returnExpect) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.phone = phone;
		this.address = address;
		this.secessionFlag = secessionFlag;
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookGr = bookGr;
		this.enrollDate = enrollDate;
		this.bookRcount = bookRcount;
		this.rentYn = rentYn;
		this.reserveNo = reserveNo;
		this.rentDate = rentDate;
		this.returnExpect = returnExpect;
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


	public String getMemberPw() {
		return memberPw;
	}


	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
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


	public int getReserveNo() {
		return reserveNo;
	}


	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}


	public Date getRentDate() {
		return rentDate;
	}


	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}


	public Date getReturnExpect() {
		return returnExpect;
	}


	public void setReturnExpect(Date returnExpect) {
		this.returnExpect = returnExpect;
	}


	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + ", memberName="
				+ memberName + ", phone=" + phone + ", address=" + address + ", secessionFlag=" + secessionFlag
				+ ", bookNo=" + bookNo + ", bookName=" + bookName + ", bookWriter=" + bookWriter + ", bookGr=" + bookGr
				+ ", enrollDate=" + enrollDate + ", bookRcount=" + bookRcount + ", rentYn=" + rentYn + ", reserveNo="
				+ reserveNo + ", rentDate=" + rentDate + ", returnExpect=" + returnExpect + "]";
	}


	
	
	
	


}
