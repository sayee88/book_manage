package edu.kh.jdbc.member.service;

import edu.kh.jdbc.member.dao.MemberDAO;
import edu.kh.jdbc.member.vo.Member;
import edu.kh.jdbc.member.vo.Reserve;

import static edu.kh.jdbc.common.JDBCTemplate.getConnection;
import static edu.kh.jdbc.common.JDBCTemplate.close;
import static edu.kh.jdbc.common.JDBCTemplate.commit;
import static edu.kh.jdbc.common.JDBCTemplate.rollback;

import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	
	/**
	 * 아이디 중복 검사 Service
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int duplicateCheck(String memberId) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.duplicateCheck(conn, memberId);
		
		close(conn);
		
		return result;
	}


	
	/**
	 * 회원가입 Service 
	 * @param signUpMember
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member signUpMember) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, signUpMember);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}



	/**
	 * 회원 로그인 Service
	 * @param mem
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Member mem) throws Exception {
		
		Connection conn = getConnection();
		
		Member loginMember = dao.login(conn, mem);
		
		close(conn);
		
		return loginMember;
	}



	/**
	 * 내 정보 수정 Service
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int updateMyInfo(Member updateMember) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.udateMyInfo(conn, updateMember);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}



	
	/**
	 * 회원 비밀번호 변경 Service
	 * @param currentPw
	 * @param newPw
	 * @param newPw2
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(String newPw, String currentPw, int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updatePw(memberNo, newPw, currentPw, conn);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}



	
	/**
	 * 회원 탈퇴 Service
	 * @param memberNo
	 * @param memberPw
	 * @return result
	 * @throws Exception
	 */
	public int secession(int memberNo, String memberPw) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.secession(memberNo, memberPw, conn);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}



	/**
	 * 도서 검색
	 * @return bookList
	 * @throws Exception
	 */
	public List<Member> searchBook(int menuNum, String keyword) throws Exception {
		
		Connection conn = getConnection();
		
		List<Member> bookList = dao.searchBook(conn, menuNum, keyword);
		
		close(conn);
		
		return bookList;
	}



	
	/**
	 * 도서 대여 가능여부 Service
	 * @param bookNo
	 * @return result
	 * @throws Exception
	 */
	public int bookRentYn(int bookNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.bookRentYn(conn, bookNo);
		
		close(conn);
		
		return result;
	}



	/**
	 * 도서 rentYn update Service
	 * @param bookNo
	 * @return result2
	 * @throws Exception
	 */
	public int updateRent(int bookNo, int bookRcount) throws Exception {
		
		Connection conn = getConnection();
		
		int result2 = dao.updateRent(conn, bookNo, bookRcount);
		
		if(result2 > 0) commit(conn);
		else rollback(conn);
		
		return result2;
	}



	/**
	 * 도서 예약 Service
	 * @param bookNo
	 * @param loginMember
	 * @return reserve
	 * @throws Exception
	 */
	public int insertRev(int bookNo, Member loginMember) throws Exception {
		
		Connection conn = getConnection();
		
		int reserve = dao.insertRev(conn, bookNo, loginMember);
		
		close(conn);
		
		return reserve;
	}

	
	
	/**
	 * 도서 반납
	 * @param reserveNo
	 * @return result
	 * @throws Exception
	 */
	public int returnBook(int reserveNo, Member loginMember) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.returnBook(conn, loginMember, reserveNo);
		
		close(conn);
		
		return result;
	}
	
	
	
	/**
	 * 도서 반납 rentYN update Service
	 * @param bookNo
	 * @param loginMember
	 * @return result2
	 * @throws Exception
	 */
	public int updateReturn(int bookNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result2 = dao.updateReturn(conn, bookNo);
		
		if(result2 > 0) commit(conn);
		else rollback(conn);
		
		return result2;
		
	}
	
	

	
	
	/**
	 * 나의 대여기록 조회 Service
	 * @param loginMember
	 * @return revList
	 * @throws Exception
	 */
	public List<Member> myRentInfo(int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		List<Member> revList = dao.myRentInfo(conn, memberNo);
		
		close(conn);
		
		return revList;
	}



	
	/**
	 * 인기 도서 (1~5위) Service
	 * @return bookList
	 * @throws Exception
	 */
	public List<Member> bestSeller() throws Exception{
		
		Connection conn = getConnection();
		
		List<Member> bookList = dao.bestSeller(conn);
		
		close(conn);
		
		return bookList;
	}



	


	



	



	
	



	
	









	



	



	
	



	



	



	
	


	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
