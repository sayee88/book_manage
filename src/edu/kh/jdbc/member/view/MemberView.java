package edu.kh.jdbc.member.view;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.member.service.MemberService;
import edu.kh.jdbc.member.vo.Member;
import edu.kh.jdbc.member.vo.Reserve;

public class MemberView {

	private Scanner sc = new Scanner(System.in);
	
	private MemberService service = new MemberService();

	
	/**
	 * 회원용 회원가입 출력용 메서드
	 */
	public void signUp() {
		System.out.println("[회원 가입]");
		
		try {
			String memberId = null;
			String memberPw = null;
			String memberPw2 = null;
			String memberName = null;
			String phone = null;
			String address = null;
			
			while(true) {
				
				System.out.print("신규 아이디 입력 : ");
				memberId = sc.next();
				
				int result = service.duplicateCheck(memberId);
				
				if(result == 0) {
					System.out.println("\n사용 가능한 아이디 입니다.\n");
					break;
				} else {
					System.out.println("\n이미 사용중인 아이디입니다. 다시 입력해주세요.\n");
				}
			}
			
			while(true) {
				
				System.out.print("비밀번호 입력 : ");
				memberPw = sc.next();
				
				System.out.print("비밀번호 확인 : ");
				memberPw2 = sc.next();
				
				if(memberPw.equals(memberPw2)) {
					break;
				} else {
					System.out.println("\n비밀번호가 일치하지 않습니다. 다시 입력해주세요.\n");
				}
			}
			
			System.out.print("회원 이름 입력 : ");
			memberName = sc.next();
			
			System.out.print("회원 전화번호 입력 : ");
			phone = sc.next();
			
			System.out.print("회원 주소 입력 : ");
			address = sc.next();
			
			Member signUpMember = new Member();
			signUpMember.setMemberId(memberId);
			signUpMember.setMemberPw(memberPw);
			signUpMember.setMemberName(memberName);
			signUpMember.setPhone(phone);
			signUpMember.setAddress(address);
			
			
			int result = service.signUp(signUpMember);
			
			if(result > 0) {
				System.out.println("\n[*** 회원가입 성공 ***]\n");
			} else {
				System.out.println("\n[회원 가입 실패]\n");
			}
			
		} catch (Exception e) {
			System.out.println("\n<회원가입 중 예외 발생>\n");
			e.printStackTrace();
			
		}
	}


	
	/**
	 * 회원 로그인
	 * @return loginMember
	 */
	public Member login() {
		
		System.out.println("[로그인]");
		
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();
		
		Member mem = new Member();
		mem.setMemberId(memberId);
		mem.setMemberPw(memberPw);
		
		Member loginMember = null;
		
		try {

			loginMember = service.login(mem);
			
			if(loginMember != null) {
				System.out.println("\n***" + loginMember.getMemberName() + "님 환영합니다. ***\n");
			} else {
				System.out.println("\n아이디 또는 비밀번호가 일치하지 않습니다.\n");
			}
		
		} catch (Exception e) {
			System.out.println("\n<로그인 과정에서 예외 발생>\n");
			e.printStackTrace();
		}
		
		
		return loginMember;
	}
	
	
	
	
	/**
	 * 내 정보 조회
	 * @param loginMember
	 */
	public void MyInfo(Member loginMember) {
		
		System.out.println("\n[내 정보 조회]\n");
		
		System.out.println("회원 번호 : " + loginMember.getMemberNo());
		System.out.println("아이디 : " + loginMember.getMemberId());
		System.out.println("이름 : " + loginMember.getMemberName());
		System.out.println("연락처 : " + loginMember.getPhone());
		System.out.println("주소 : " + loginMember.getAddress());
		
	}
	
	

	/**
	 * 내 정보 수정
	 * @param loginMember
	 */
	public void updateMyInfo(Member loginMember) {
		
		System.out.println("\n[내 정보 수정(연락처,주소)]\n");
		
		System.out.print("변경할 연락처 : ");
		String phone = sc.next();
		
		System.out.print("변경할 주소 : ");
		String address = sc.next();
		
		Member updateMember = new Member();
		updateMember.setPhone(phone);
		updateMember.setAddress(address);
		updateMember.setMemberNo(loginMember.getMemberNo());
		
		
		try {
			
			int result = service.updateMyInfo(updateMember);
			
			if(result > 0) {
				System.out.println("\n내 정보가 수정되었습니다.\n");
				
				loginMember.setPhone(phone);
				loginMember.setAddress(address);
				
			} else {
				System.out.println("\n회원 정보 수정에 실패하였습니다.\n");
			}
		} catch (Exception e) {
			System.out.println("\n<내 정보 수정 중 예외 발생>\n");
			e.printStackTrace();
			
		}
	}



	
	/**
	 * 비밀번호 변경
	 */
	public void updatePw(Member loginMember) {
		
		System.out.println("\n[비밀번호 변경]\n");
		
		System.out.print("현재 비밀번호 : ");
		String currentPw = sc.next();
		
		String newPw = null;
		String newPw2 = null;
		
		while(true) {
		
			System.out.print("새 비밀번호 : ");
			newPw = sc.next();
			
			System.out.print("새 비밀번호 확인 : ");
			newPw2 = sc.next();
			
			if(newPw.equals(newPw2)) {
				break;
			} else {
				System.out.println("\n<비밀번호가 일치하지 않습니다.>\n");
			}
		}
		
		
		try {
			
			int result = service.updatePw(newPw, currentPw, loginMember.getMemberNo());
			
			if(result > 0) {
				System.out.println("\n비밀번호가 변경되었습니다.\n");
				loginMember.setMemberPw(newPw);
				
			} else {
				System.out.println("\n비밀번호 변경 실패\n");
			}
			
		} catch (Exception e) {
			System.out.println("\n<비밀번호 변경 중 예외 발생>\n");
			sc.nextLine();
			e.printStackTrace();
			
		}
		
		
		
		
		
		
		
	}



	/**
	 * 회원 탈퇴 
	 * @param loginMember
	 * @return result
	 */
	public int secession(Member loginMember) {
		
		System.out.println("\n[회원 탈퇴]\n");
		
		char ch = ' ';
		
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();
		
		
		while(true) {
		
			System.out.print("정말로 탈퇴하시겠습니까?(Y/N) : ");
			ch = sc.next().toUpperCase().charAt(0);
			
			if(ch == 'Y' || ch == 'N' ) {
				break;
			} else {
				System.out.println("Y 또는 N을 입력해주세요.\n");
			}
		}
		
		try {
		
			if(ch == 'Y') {
				
				String cap = captcha();
				System.out.println("다음 보안문자를 입력해주세요 >> " + cap);
				System.out.print("보안 문자 입력 : ");
				String input = sc.next();
				
				if(input.equals(cap)) {
					
					int result = service.secession(loginMember.getMemberNo(), memberPw);
					
					if(result > 0) {
						System.out.println("\n[탈퇴 되었습니다.]\n");
					} else {
						System.out.println("\n비밀번호가 일치하지 않습니다.\n");
					}
					
					return result;
					
				} else {
					System.out.println("\n보안문자가 일치하지 않습니다.(탈퇴 취소)\n");
				}
			}else {
				System.out.println("\n탈퇴 취소\n");
			}
		
		} catch(Exception e) {
			System.out.println("\n<회원 탈퇴 중 예외 발생>\n");
			e.printStackTrace();
		}
		return 0;
	}



	/**
	 * 보안 문자 
	 * @return cap
	 */
	private String captcha() {
		
		String cap = "";
		
		for(int i = 0; i < 5 ; i++ ) {
			cap += (char)(Math.random() * 26 + 'a');
		}
		
		return cap;
	}



	/**
	 * 도서 검색
	 */
	public void searchBook() {
		
		System.out.println("\n[도서 검색]\n");
		
		int menuNum = -1;
		
		do {
			
			try {
				
				System.out.println("1. 제목");
				System.out.println("2. 작가");
				System.out.println("3. 분류");
				System.out.println("0. 이전 메뉴");
				
				System.out.print("메뉴 선택 >> ");
				menuNum = sc.nextInt();
				sc.nextLine();
				
				switch(menuNum){
				case 0 : System.out.println("\n이전 메뉴로 ...\n");break;	
				case 1 : case 2 : case 3 : 
					
					System.out.print("검색 >> ");
					String keyword = sc.next();
					
					List<Member> bookList = service.searchBook(menuNum, keyword);
					
					if(bookList.isEmpty()) {
						System.out.println("\n조회된 도서가 없습니다.\n");
					} else {
							
						System.out.println("----------------------------------------------------------------");
						System.out.printf("%3s     %s     %s     %s     ",
								"도서번호", "도서제목", "작가", "분류");
						System.out.println("\n----------------------------------------------------------------");
						
						for(Member mem : bookList) {
							
							System.out.printf("%d     %s     %s     %s     \n", 
									mem.getBookNo(), mem.getBookName(), mem.getBookWriter(), mem.getBookGr());
							
						}
					}
					
					return;
				
				default : System.out.println("\n메뉴에 작성된 번호를 입력해주세요.\n");
				
				}
				
			} catch(InputMismatchException e) {
				System.out.println("\n입력 형식이 올바르지 않습니다. 다시 입력해주세요.\n");
				sc.nextLine();
				
			} catch(Exception e) {
				System.out.println("\n<도서 검색 중 예외 발생>\n");
				e.printStackTrace();
			}
			
			
		} while(menuNum != 0);
		
	}



	/**
	 * 도서 대여 메뉴
	 */
	public void bookRent(Member loginMember) {
		
		System.out.println("\n[도서 대여]\n");
		
		try {
			
			int menuNum = 0;
			
			do {
				try {
					
					System.out.println("1. 도서 검색");
					System.out.println("0. 이전메뉴");
					
					System.out.print("메뉴를 선택해주세요 : ");
					menuNum = sc.nextInt();
					sc.nextLine();
					
					switch(menuNum) {
					case 1 : updateRent(loginMember); break;
					case 0 : System.out.println("\n이전 메뉴로 ...\n");break;
					default : System.out.println("\n메뉴에 작성된 번호를 입력하세요.\n");
					
					}
					
					
				} catch (Exception e) {
					System.out.println("\n<도서 대여 확인 중 예외 발생>\n");
				}
			} while(menuNum != 0);
			
		} catch(Exception e) {
			System.out.println("\n<도서 대여 중 예외 발생>\n");
			e.printStackTrace();
		}
		
		
		
	}



	/**
	 * 도서 대여
	 * @param loginMember
	 */
	private void updateRent(Member loginMember) {
		
		System.out.println("\n[도서 대여]\n");
		
		int bookNo = 0;
		int bookRcount = 0;
		
		try {
		
			// 도서 대여 가능 여부
			System.out.print("도서 번호 검색 : ");
			bookNo = sc.nextInt();
			sc.nextLine();
		
			int result = service.bookRentYn(bookNo);
			
			if(result > 0) {
				System.out.println("\n[대여 가능합니다.]\n");
				
			} else {
				System.out.println("\n[대여 중인 도서입니다.]\n");
			}
			
			if(result > 0) {
				
				System.out.print("대여 하시겠습니까?(Y/N) : ");
				char ch = sc.next().toUpperCase().charAt(0);
				
				if(ch == 'Y') {
					
					int result2 = service.updateRent(bookNo, bookRcount);
					
				} else {
					System.out.println("\n대여 취소\n");
				}
				
				if(ch == 'Y') {
					
					int reserve = service.insertRev(bookNo, loginMember);
					
					if(reserve > 0) {
						System.out.println("\n[대여 되었습니다.]\n");
					}else {
						System.out.println("\n대여 실패\n");
					}
				} 
			}
			
		} catch(Exception e) {
			System.out.println("\n<도서 대여 중 예외 발생>\n");
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * 도서 반납
	 * @param loginMember
	 */
	public void retrunBook(Member loginMember) {
		
		System.out.println("\n[도서 반납]\n");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일 HH:MM");
		Calendar time = Calendar.getInstance();
		
		String format_time = format.format(time.getTime());
		
		try {
			
			System.out.print("도서 번호 입력 : ");
			int bookNo = sc.nextInt();
			sc.nextLine();
			
			System.out.print("예약 번호 입력 : ");
			int reserveNo = sc.nextInt();
			sc.nextLine();
			
			System.out.print("\n" + format_time + " 현재 시간으로 반납하시겠습니까?(Y/N) : ");
			char ch = sc.next().toUpperCase().charAt(0);
			
			if(ch == 'Y') {
				
				int result2 = service.updateReturn(bookNo);
				
			} else {
				System.out.println("\n반납 취소\n");
			}
			
			if(ch == 'Y') {
				
				int result = service.returnBook(reserveNo, loginMember);
				
				if(result > 0) {
					System.out.println("\n[반납 완료.]\n");
				} else {
					System.out.println("\n반납 실패.\n");
				}
			} 
		
		} catch(Exception e) {
			System.out.println("\n<도서 반납 중 예외 발생>\n");
			e.printStackTrace();
		}
		
	}



	/**
	 * 나의 대여기록 조회
	 */
	public void myRentInfo(Member loginMember) {
		
		System.out.println("\n[나의 대여기록 조회]\n");
		
		try {
		
			List<Member> revList = service.myRentInfo(loginMember.getMemberNo());
			
			
			System.out.println("------------------------------------------------------------------------------");
			System.out.printf("%s     %s     %s     %s     %s     %s     ",
					"예약번호", "도서번호", "도서명", "작가", "대여일", "반납예정일");
			System.out.println("\n----------------------------------------------------------------------------");
		
			for(Member mem : revList) {
				
				System.out.printf("%3d     %10d     %5s     %5s     %5s     %5s     \n",
						mem.getReserveNo(), mem.getBookNo(), mem.getBookName(),
						mem.getBookWriter(), mem.getRentDate(), mem.getReturnExpect());
				
			}
			
			System.out.println();
		
		
		} catch(Exception e) {
			System.out.println("\n<나의 대여기록 조회 중 예외발생>\n");
			e.printStackTrace();
		}
		
	}



	/**
	 * 인기도서(1~5위)
	 */
	public void bestSeller() {
		
		System.out.println("\n[인기 도서 순위]\n");
		
		try {
		
		List<Member> bookList = service.bestSeller();
		
		System.out.println("------------------------------------------------------------------------------");
		System.out.printf("%3s     %5s     %10s     %10s     %5s     %5s", "순위", "도서명", "작가", "분류","대여 횟수", "도서번호");
		System.out.println("\n----------------------------------------------------------------------------");
		
		for (Member mem : bookList) {
			System.out.printf("%5d     %10s     %10s     %10s    %2d     %2d     \n",
					mem.getRank(), mem.getBookName(), mem.getBookWriter(), 
					mem.getBookGr(), mem.getBookRcount(), mem.getBookNo());
		
		}
		
		} catch(Exception e) {
			System.out.println("\n<인기도서 조회 중 예외발생>\n");
			e.printStackTrace();
		}
		
		
	}



	







	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
