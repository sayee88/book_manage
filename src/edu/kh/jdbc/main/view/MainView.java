package edu.kh.jdbc.main.view;

import java.util.Scanner;

import edu.kh.jdbc.manager.view.ManagerView;
import edu.kh.jdbc.manager.vo.Manager;
import edu.kh.jdbc.member.view.MemberView;
import edu.kh.jdbc.member.vo.Member;

public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	
	private Member loginMember = null;
	
	private Manager loginManager = null;
	
	private MemberView memberView = new MemberView();
	
	private ManagerView managerView = new ManagerView();

		
	public void displayMenu() {
		
		int menuNum = -1;
		
		do {
			try {
				
					System.out.println("\n********** SAY 도서관 **********\n");
					
					System.out.println("1. 관리자 로그인");
					System.out.println("2. 회원 로그인");
					System.out.println("0. 프로그램 종료");
					
					System.out.print("메뉴를 선택해 주세요 >> ");
					menuNum = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch(menuNum) {
					case 1 : managerLogin(); break;
					case 2 : memberLoginView(); break;
					case 0 : System.out.println("프로그램 종료 ... "); break;
					default : System.out.println("메뉴에 있는 번호를 입력해주세요.");
					
					}
					
			} catch(Exception e) {
				System.out.println("\n<입력형식이 올바르지 않습니다.>\n");
				e.printStackTrace();
				
			}
			
		} while (menuNum != 0);
		
		
		
	}


	
	/**
	 * 관리자 로그인 뷰
	 */
	private void managerLogin() {
		
		int menuNum = -1;
		
		do {
			
			try {
				
				System.out.println("\n[관리자 로그인]\n");
				
				System.out.println("1. 로그인");
				System.out.println("0. 이전 메뉴");
				
				System.out.print("메뉴를 선택해주세요 >> ");
				menuNum = sc.nextInt();
				
				
				switch(menuNum) {
				case 1 : managerLoginView(); break;
				case 0 : System.out.println("\n이전 메뉴로 ... \n");break;
				default : System.out.println("\n메뉴에 작성된 번호를 입력해주세요.\n");
				
				}
					
			} catch (Exception e) {
				System.out.println("\n<입력형식이 올바르지 않습니다. 다시 시도해주세요.>\n");
				sc.nextLine();
			}
		
		} while (menuNum != 0);
		
		
		
		
	}



	/**
	 * 관리자 로그인
	 */
	private void managerLoginView() {
		
		System.out.println("\n[관리자 로그인]\n");
		
		loginManager = new Manager();
		loginManager.setManagerId("manager");
		loginManager.setManagerPw("1234");
		
		String inputId = null;
		String inputPw = null;
		
		while(true) {
		
			System.out.print("아이디 : ");
			inputId = sc.next();
			
			System.out.print("비밀번호 : ");
			inputPw = sc.next();
			
			if(loginManager.getManagerId().equals(inputId) && loginManager.getManagerPw().equals(inputPw)) {
				
				System.out.println("\n관리자모드로 로그인 되었습니다.\n");
				break;
			
			} else {
				System.out.println("\n아이디와 비밀번호가 일치하지 않습니다.\n");
			}
		}
		
		int menuNum = -1;
		
		do {
		
			try {
			
				if(loginManager.getManagerId().equals(inputId) && loginManager.getManagerPw().equals(inputPw)) {
					
					System.out.println("\n********** 관리자 메뉴 **********\n");
					
					System.out.println("1. 도서 전체 조회");
					System.out.println("2. 도서 검색(제목/작가/도서번호/분류)");
					System.out.println("3. 도서 등록");
					System.out.println("4. 도서 삭제");
					System.out.println("5. 도서 수정");
					System.out.println("6. 대여 중인 도서/회원 목록");
					System.out.println("7. 회원 전체 조회");
					System.out.println("0. 로그아웃");
					
					System.out.print("\n메뉴를 선택해주세요 >> ");
					menuNum = sc.nextInt();
					sc.nextLine();
					
					switch(menuNum) {
					case 1 : managerView.bookSellectAll(); break;
					case 2 : managerView.searchBook(); break;
					case 3 : managerView.insertBook(); break;
					case 4 : managerView.deleteBook(loginManager); break;
					case 5 : managerView.updateBook(); break;
					case 6 : managerView.rentBookMember(); break;
					case 7 : managerView.memberSellectAll(); break; // 실행할 SQL 문은 비어 있거나 널일 수 없음
					case 0 : loginManager = null; break;
					default : System.out.println("\n메뉴에 작성된 번호를 입력하세요.\n");
					
					}
					
				} else {
					System.out.println("\n아이디와 비밀번호가 일치하지 않습니다.\n");
				}
				
			} catch(Exception e) {
				e.printStackTrace();
		}
		
		} while(menuNum != 0);
	
	}



	/**
	 * 회원 로그인 뷰
	 */
	private void memberLoginView() {
		
		int menuNum = -1;
		
		do {
			
			try {
				
				if(loginMember == null) { // 로그인이 되어 있지 않은 경우
				
					System.out.println("[회원 로그인]");
					
					System.out.println("1. 로그인");
					System.out.println("2. 회원가입");
					System.out.println("0. 이전 메뉴");
					
					System.out.print("메뉴를 선택해주세요 >> ");
					menuNum = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch(menuNum) {
					case 1 : loginMember = memberView.login(); break;
					case 2 : memberView.signUp(); break;
					case 0 : System.out.println("이전 메뉴로 ... ");break;
					default : System.out.println("\n<메뉴에 작성된 번호를 입력해주세요>\n");
					
					}
					
				} else {
					
					System.out.println("\n********** 회원 전용 메뉴 *********\n");
					
					System.out.println("1. 인기 도서 순위");
					System.out.println("2. 도서 검색(제목/작가/분류)");
					System.out.println("3. 도서 대여");
					System.out.println("4. 도서 반납");
					System.out.println("5. 나의 대여기록 조회");
					System.out.println("6. 내 정보 조회");
					System.out.println("7. 내 정보 수정(연락처,주소)");
					System.out.println("8. 비밀번호 변경");
					System.out.println("9. 회원 탈퇴");
					System.out.println("0. 로그아웃");
					
					System.out.print("메뉴를 선택해 주세요 >> ");
					menuNum = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch(menuNum) {
					case 1 : memberView.bestSeller(); break;
					case 2 : memberView.searchBook(); break;
					case 3 : memberView.bookRent(loginMember);break;
					case 4 : memberView.retrunBook(loginMember); break;
					case 5 : memberView.myRentInfo(loginMember); break;
					case 6 : memberView.MyInfo(loginMember); break;
					case 7 : memberView.updateMyInfo(loginMember); break;
					case 8 : memberView.updatePw(loginMember); break;
					case 9 : 
						
						int result = memberView.secession(loginMember);
						
						if(result > 0) loginMember = null;
						break;
						
					case 0 : loginMember = null; break;
					default : System.out.println("\n메뉴에 작성된 번호를 입력하세요.\n");
					
					}
				}
			
			}catch(Exception e) {
				System.out.println("\n<입력 형식이 올바르지 않습니다. 다시 시도해주세요.>\n");
				sc.nextLine();
			}
		
		} while ( menuNum != 0);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
