package edu.kh.jdbc.member.dao;

import static edu.kh.jdbc.common.JDBCTemplate.getConnection;
import static edu.kh.jdbc.common.JDBCTemplate.close;
import static edu.kh.jdbc.common.JDBCTemplate.commit;
import static edu.kh.jdbc.common.JDBCTemplate.rollback;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.member.vo.Member;
import edu.kh.jdbc.member.vo.Reserve;

public class MemberDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public MemberDAO() {
		
		try {
			
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-sql.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 아이디 중복검사 DAO
	 * @param conn
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int duplicateCheck(Connection conn, String memberId) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_ID = ? AND SECESSION_FL = 'N'";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
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
	 * 회원가입 DAO
	 * @param conn
	 * @param signUpMember
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member signUpMember) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, signUpMember.getMemberId());
			pstmt.setString(2, signUpMember.getMemberPw());
			pstmt.setString(3, signUpMember.getMemberName());
			pstmt.setString(4, signUpMember.getPhone());
			pstmt.setString(5, signUpMember.getAddress());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	
	/**
	 * 회원 로그인 DAO
	 * @param conn
	 * @param mem
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Connection conn, Member mem) throws Exception {
		
		Member loginMember = null;
		
		try {
			
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem.getMemberId());
			pstmt.setString(2, mem.getMemberPw());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int memberNo = rs.getInt("MEMBER_NO");
				String memberId = rs.getString("MEMBER_ID");
				String memberName = rs.getString("MEMBER_NM");
				String phone = rs.getString("PHONE");
				String address = rs.getString("ADDRESS");
				
				
				loginMember = new Member();
				loginMember.setMemberNo(memberNo);
				loginMember.setMemberId(memberId);
				loginMember.setMemberName(memberName);
				loginMember.setPhone(phone);
				loginMember.setAddress(address);
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return loginMember;
	}


	
	/**
	 * 내 정보 수정 DAO
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int udateMyInfo(Connection conn, Member updateMember) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateMyInfo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updateMember.getPhone());
			pstmt.setString(2, updateMember.getAddress());
			pstmt.setInt(3, updateMember.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	
	/**
	 * 회원 비밀번호 변경 DAO
	 * @param memberPw
	 * @param newPw
	 * @param newPw2
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(int memberNo, String newPw, String currentPw, Connection conn) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updatePw");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPw);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, currentPw);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(conn);
		}
		
		return result;
	}


	
	/**
	 * 회원 탈퇴 DAO
	 * @param memberNo
	 * @param memberPw
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int secession(int memberNo, String memberPw, Connection conn) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("secession");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, memberPw);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(conn);
		}
		
		return result;
	}


	
	/**
	 * 도서 검색
	 * @param conn
	 * @return bookList
	 * @throws Exception
	 */
	public List<Member> searchBook(Connection conn, int menuNum, String keyword) throws Exception {
		
		List<Member> bookList = new ArrayList<Member>();
		
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
				
				Member book = new Member();
				book.setBookNo(bookNo);
				book.setBookName(bookName);
				book.setBookWriter(bookWriter);
				book.setBookGr(bookGr);
				
				bookList.add(book);
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return bookList;
	}


	
	/**
	 * 도서 대여 가능 여부 DAO
	 * @param conn
	 * @param bookNo
	 * @return result
	 * @throws Exception
	 */
	public int bookRentYn(Connection conn, int bookNo) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = "SELECT * FROM BOOK WHERE BOOK_NO = ? AND RENT_YN = 'N'";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bookNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				result = rs.getInt(1);
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}


	
	/**
	 * 도서 rentYn update DAO
	 * @param conn
	 * @param bookNo
	 * @return result
	 * @throws Exception
	 */
	public int updateRent(Connection conn, int bookNo, int bookRcount) throws Exception {
		
		int result2 = 0;
		
		try {
			
			String sql = "UPDATE BOOK SET RENT_YN = 'Y', BOOK_RCOUNT = ? WHERE BOOK_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bookRcount += 1);
			pstmt.setInt(2, bookNo);
			
			result2 = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result2;
	}


	
	/**
	 * 도서 예약 DAO
	 * @param bookNo
	 * @param loginMember
	 * @return reserve
	 * @throws Exception
	 */
	public int insertRev(Connection conn, int bookNo, Member loginMember) throws Exception {
		
		int reserve = 0;
		
		try {
			
			String sql = prop.getProperty("insertRev");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, loginMember.getMemberNo());
			pstmt.setInt(2, bookNo);
			
			reserve = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return reserve;
	}
	
	
	

	/**
	 * 도서 반납 DAO
	 * @param conn
	 * @param reserveNo
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int returnBook(Connection conn, Member loginMember, int reserveNo) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("returnBook");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reserveNo);
			pstmt.setInt(2, loginMember.getMemberNo());
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	/**
	 * 도서 반납 rentYn update DAO
	 * @param conn
	 * @param bookNo
	 * @param loginMember
	 * @return result2
	 * @throws Exception
	 */
	public int updateReturn(Connection conn, int bookNo) throws Exception {
		
		int result2 = 0;
		
		try {
			
			String sql = "UPDATE BOOK SET RENT_YN = 'N' WHERE BOOK_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bookNo);
			
			result2 = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result2;
	}
	
	


	
	/**
	 * 나의 대여기록 조회 DAO
	 * @param conn
	 * @param loginMember
	 * @return revList
	 * @throws Exception
	 */
	public List<Member> myRentInfo(Connection conn, int memberNo) throws Exception {
		
		List<Member> revList = new ArrayList<Member>();
		
		try {
			
			String sql = prop.getProperty("myRentInfo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int reserveNo = rs.getInt("REV_NO");
				int bookNo = rs.getInt("BOOK_NO");
				String bookName = rs.getString("BOOK_NM");
				String bookWrite = rs.getString("BOOK_WRITER");
				Date rentDate = rs.getDate("RENT_DATE");
				Date returnExpect = rs.getDate("RETURN_EXPECT");
				
				Member reserve = new Member();
				reserve.setReserveNo(reserveNo);
				reserve.setBookNo(bookNo);
				reserve.setBookName(bookName);
				reserve.setBookWriter(bookWrite);
				reserve.setRentDate(rentDate);
				reserve.setReturnExpect(returnExpect);
				
				revList.add(reserve);
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return revList;
	}


	

	/**
	 * 인기 도서(1~5위)
	 * @param conn
	 * @return bookList
	 * @throws Exception
	 */
	public List<Member> bestSeller(Connection conn) throws Exception {
		
		List<Member> bookList = new ArrayList<Member>();
		
		try {
			
			String sql = prop.getProperty("bestSeller");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int bookNo = rs.getInt("BOOK_NO");
				String bookName = rs.getString("BOOK_NM");
				String bookWrite = rs.getString("BOOK_WRITER");
				String bookGr = rs.getString("BOOK_GR");
				int bookRcount = rs.getInt("BOOK_RCOUNT");
				int rank = rs.getInt("순위");
				
				Member book = new Member();
				book.setBookNo(bookNo);
				book.setBookName(bookName);
				book.setBookWriter(bookWrite);
				book.setBookGr(bookGr);
				book.setBookRcount(bookRcount);
				book.setRank(rank);
				
				bookList.add(book);
				
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return bookList;
	}


	


	


	


	




	
	
	


	


	
	


	
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
