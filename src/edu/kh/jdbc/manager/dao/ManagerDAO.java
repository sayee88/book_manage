package edu.kh.jdbc.manager.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplate.getConnection;
import static edu.kh.jdbc.common.JDBCTemplate.close;
import static edu.kh.jdbc.common.JDBCTemplate.commit;
import static edu.kh.jdbc.common.JDBCTemplate.rollback;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.manager.vo.Manager;
import edu.kh.jdbc.member.vo.Member;

public class ManagerDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private Properties prop = null;
	
	
	public ManagerDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("manager-sql.xml"));
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 회원 전체 조회 DAO
	 * @return memberList
	 * @throws Exception
	 */
	public List<Manager> memberSellectAll(Connection conn) throws Exception {
		
		List<Manager> memberList = new ArrayList<Manager>();
		
		try {
			
			String sql = prop.getProperty("memberSellectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int memberNo = rs.getInt("MEMBER_NO");
				String memberId = rs.getString("MEMBER_ID");
				String memberName = rs.getString("MEMBER_NM");
				String phone = rs.getString("PHONE");
				String address = rs.getString("ADDRESS");
				
				Manager member = new Manager();
				member.setMemberNo(memberNo);
				member.setMemberId(memberId);
				member.setMemberName(memberName);
				member.setPhone(phone);
				member.setAddress(address);
				
				memberList.add(member);
				
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return memberList;
	}


	
	/**
	 * 도서 등록 DAO
	 * @param conn
	 * @param book
	 * @return result
	 * @throws Exception
	 */
	public int insertBook(Connection conn, Manager book) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insertBook");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getBookWriter());
			pstmt.setString(3, book.getBookGr());
			
			
			
			result = pstmt.executeUpdate();
			
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	
	/**
	 * 도서 삭제
	 * @param conn
	 * @param inputBookNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBook(Connection conn, int inputBookNo) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("deleteBook");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, inputBookNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
		
		
	}


	
	/**
	 * 도서 전체 조회 
	 * @param conn
	 * @return bookList
	 * @throws Exception
	 */
	public List<Manager> bookSellectAll(Connection conn) throws Exception {
		
		List<Manager> bookList = new ArrayList<Manager>();
		
		try {
			
			String sql = prop.getProperty("bookSellectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int bookNo = rs.getInt("BOOK_NO");
				String bookName = rs.getString("BOOK_NM");
				String bookWriter = rs.getString("BOOK_WRITER");
				String bookGr = rs.getString("BOOK_GR");
				Date enrollDate = rs.getDate("ENROLL_DATE");
				int bookRcount = rs.getInt("BOOK_RCOUNT");
				
				Manager book = new Manager();
				book.setBookNo(bookNo);
				book.setBookName(bookName);
				book.setBookWriter(bookWriter);
				book.setBookGr(bookGr);
				book.setEnrollDate(enrollDate);
				book.setBookRcount(bookRcount);
				
				bookList.add(book);
				
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return bookList;
	}


	
	/**
	 * 도서 검색
	 * @param conn
	 * @param menuNum
	 * @param keyword
	 * @return bookList
	 * @throws Exception
	 */
	public List<Manager> searchBook(Connection conn, int menuNum, String keyword) throws Exception {
		
		List<Manager> bookList = new ArrayList<Manager>();
		
		try {
			
			String sql = prop.getProperty("searchBook1")
					+ prop.getProperty("condition" + menuNum)
					+ prop.getProperty("searchBook2");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int bookNo = rs.getInt("BOOK_NO");
				String bookName = rs.getString("BOOK_NM");
				String bookWriter = rs.getString("BOOK_WRITER");
				String bookGr = rs.getString("BOOK_GR");
				Date enrollDate = rs.getDate("ENROLL_DATE");
				int bookRcount = rs.getInt("BOOK_RCOUNT");
				
				Manager book = new Manager();
				book.setBookNo(bookNo);
				book.setBookName(bookName);
				book.setBookWriter(bookWriter);
				book.setBookGr(bookGr);
				book.setEnrollDate(enrollDate);
				book.setBookRcount(bookRcount);
				
				bookList.add(book);
				
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return bookList;
	}


	
	/**
	 * 수정할 도서 확인
	 * @param conn
	 * @param bookNo
	 * @return result
	 * @throws Exception
	 */
	public int checkBook(Connection conn, int bookNo) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = "SELECT COUNT(*) FROM BOOK WHERE BOOK_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bookNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}


	
	/**
	 * 도서 수정
	 * @param conn
	 * @param book
	 * @return result
	 * @throws Exception
	 */
	public int updateBook(Connection conn, Member book) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateBook");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getBookWriter());
			pstmt.setString(3, book.getBookGr());
			pstmt.setInt(4, book.getBookNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	
	
	/**
	 * 대여중인 도서/회원 DAO
	 * @return rentList
	 * @throws Exception
	 */
	public List<Manager> rentBookMember(Connection conn) throws Exception {
		
		List<Manager> rentList = new ArrayList<Manager>();
		
		try {
			
			String sql = prop.getProperty("rentBookMember");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int bookNo = rs.getInt("BOOK_NO");
				String bookGr = rs.getString("BOOK_GR");
				String bookName = rs.getString("BOOK_NM");
				int memberNo = rs.getInt("MEMBER_NO");
				String memberName = rs.getString("MEMBER_NM");
				
				Manager rent = new Manager();
				rent.setBookNo(bookNo);
				rent.setBookGr(bookGr);
				rent.setBookName(bookName);
				rent.setMemberNo(memberNo);
				rent.setMemberName(memberName);
				
				rentList.add(rent);
				
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		
		return rentList;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
