package edu.kh.jdbc.manager.service;

import static edu.kh.jdbc.common.JDBCTemplate.getConnection;
import static edu.kh.jdbc.common.JDBCTemplate.close;
import static edu.kh.jdbc.common.JDBCTemplate.commit;
import static edu.kh.jdbc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.manager.dao.ManagerDAO;
import edu.kh.jdbc.manager.vo.Manager;
import edu.kh.jdbc.member.vo.Member;

public class ManagerService {

	private ManagerDAO dao = new ManagerDAO(); 
	
	

	/**
	 * 회원 전체 조회
	 * @return memberList
	 * @throws Exception
	 */
	public List<Manager> memberSellectAll() throws Exception {
		
		Connection conn = getConnection();
		
		List<Manager> memberList = dao.memberSellectAll(conn);
		
		close(conn);
		
		return memberList;
	}



	/**
	 * 도서 등록 Service
	 * @param book
	 * @return result
	 * @throws Exception
	 */
	public int insertBook(Manager book) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.insertBook(conn, book);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}



	
	
	
	/**
	 * 도서 삭제
	 * @param inputBookNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBook(int inputBookNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.deleteBook(conn, inputBookNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}



	
	/**
	 * 도서 전체 조회
	 * @return bookList
	 * @throws Exception
	 */
	public List<Manager> bookSellectAll() throws Exception {
		
		Connection conn = getConnection();
		
		List<Manager> bookList = dao.bookSellectAll(conn);
		
		close(conn);
		
		return bookList;
	}



	/**
	 * 도서 검색
	 * @param menuNum
	 * @param keyword
	 * @return bookList
	 * @throws Exception
	 */
	public List<Manager> searchBook(int menuNum, String keyword) throws Exception {
		
		Connection conn = getConnection();
		
		List<Manager> bookList = dao.searchBook(conn, menuNum, keyword);
		
		close(conn);
		
		return bookList;
	}



	/**
	 * 수정할 도서 확인
	 * @param bookNo
	 * @return result
	 * @throws Exception
	 */
	public int checkBook(int bookNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.checkBook(conn, bookNo);
		
		close(conn);
		
		return result;
	}



	/**
	 * 도서 수정
	 * @param book
	 * @return result
	 */
	public int updateBook(Member book) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateBook(conn, book);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	
	}



	/**
	 * 대여중인 도서/회원 Service
	 * @return rentList
	 * @throws Exception
	 */
	public List<Manager> rentBookMember() throws Exception {
		
		Connection conn = getConnection();
		
		List<Manager> rentList = dao.rentBookMember(conn);
		
		close(conn);
		
		return rentList;
	}
		
		



	



	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
