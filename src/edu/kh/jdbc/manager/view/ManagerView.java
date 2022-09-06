package edu.kh.jdbc.manager.view;



import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.manager.service.ManagerService;
import edu.kh.jdbc.manager.vo.Manager;
import edu.kh.jdbc.member.vo.Member;

public class ManagerView {
	
	private Scanner sc = new Scanner(System.in);
	
	private ManagerService service = new ManagerService();

	
	/**
	 * 회원 전체 조회
	 */
	public void memberSellectAll() {
		
		System.out.println("\n[회원 전체 조회]\n");
		
		try {
		
			List<Manager> memberList = service.memberSellectAll();
		
			if(memberList.isEmpty()) {
				System.out.println("\n조회 결과가 없습니다.\n");
				
			} else {
				
				System.out.println("-------------------------------------------------------------------------");
				System.out.printf("%s     %s     %s     %s     %s     ", "회원번호", "아이디", "이름", "연락처", "주소");
				System.out.println("\n-------------------------------------------------------------------------");
				
				
				for(Manager mem : memberList) {
					
					System.out.printf("%5d  %13s  %s  %s  %s  \n",
							mem.getMemberNo(), mem.getMemberId(), mem.getMemberName(), mem.getPhone(), mem.getAddress());
					
				}
				
			}
		} catch (Exception e) {
			System.out.println("\n<회원 전체 조회 중 예외 발생>\n");
			e.printStackTrace();
		}
		
	}


	
	/**
	 * 도서 등록 
	 */
	public void insertBook() {
		
		System.out.println("\n[도서 등록]\n");
		
		try {
			
			String bookName = null;
			String bookWriter = null;
			String bookGr = null;
			
			System.out.print("등록할 도서 이름 : ");
			bookName = sc.nextLine();
			
			System.out.print("등록할 도서의 작가 : ");
			bookWriter = sc.nextLine();
			
			System.out.print("등록할 도서 분류 : ");
			bookGr = sc.nextLine();
			
			Manager book = new Manager();
			book.setBookName(bookName);
			book.setBookWriter(bookWriter);
			book.setBookGr(bookGr);
			

			
			int result = service.insertBook(book);
			
			if(result > 0) {
				System.out.println("\n[도서 등록 완료]\n");
			} else {
				System.out.println("\n도서 등록 실패.\n");
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
	}



	
	/**
	 * 도서 삭제
	 */
	public void deleteBook(Manager loginManager) {
		
		System.out.println("\n[도서 삭제]\n");
		
		while(true) {
		
			System.out.print("비밀번호 입력 : ");
			String inputPw = sc.next();
		
			if(inputPw.equals(loginManager.getManagerPw())) {
				break;
			} else {
				System.out.println("\n비밀번호가 일치하지 않습니다. 다시 입력해주세요.\n");
			}
		}
		
		
		System.out.print("삭제할 도서 번호 입력 : ");
		int inputBookNo = sc.nextInt();
		sc.nextLine();
		
		System.out.print("정말로 삭제하시겠습니까?(Y/N) : ");
		char inputYn = sc.next().toUpperCase().charAt(0);
		
		try {
			
			if(inputYn == 'Y') {
				
				int result = service.deleteBook(inputBookNo);
				
				if(result > 0) {
					System.out.println("\n[도서가 삭제 되었습니다.]\n");
					
				} else {
					System.out.println("\n해당 도서가 없습니다.\n");
				}
				
				
			} else {
				System.out.println("\n도서 삭제가 취소되었습니다.\n");
			}
			
		} catch (Exception e) {
			System.out.println("\n<도서 삭제 중 예외 발생>\n");
			e.printStackTrace();
		}
	}



	/**
	 * 도서 전체 조회
	 */
	public void bookSellectAll() {
		
		System.out.println("[도서 전체 조회]");
		
		try {
			
			List<Manager> bookList = service.bookSellectAll();
			
			if(bookList.isEmpty()) {
				System.out.println("\n조회된 도서가 없습니다.\n");
			} else {
				
				System.out.println("-----------------------------------------------------------------------------------------------------");
				System.out.printf("%3s   %5s   %10s   %11s   %8s  %10s ", "도서번호", "도서제목", "작가", "분류", "등록일", "도서 대여 횟수");
				System.out.println("\n---------------------------------------------------------------------------------------------------");
					
				for(Manager mg : bookList) {
					
					System.out.printf("%3d   %10s   %10s   %4s   %4s   %3d\n", 
							mg.getBookNo(), mg.getBookName(), mg.getBookWriter(), 
							mg.getBookGr(), mg.getEnrollDate(), mg.getBookRcount());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}



	/**
	 * 도서 검색(제목/작가)
	 */
	public void searchBook() {
		
		System.out.println("\n[도서 검색]\n");
		
		int menuNum = -1;
		
		do {
			
			try {
				
				System.out.println("=== 검색 조건 설정 ===");
				System.out.println("1. 제목");
				System.out.println("2. 작가");
				System.out.println("3. 도서번호");
				System.out.println("4. 분류");
				System.out.println("0. 이전메뉴");
				
				System.out.print("선택 >> ");
				menuNum = sc.nextInt();
				sc.nextLine();
				
				switch(menuNum) {
				case 0 : System.out.println("\n이전 메뉴로...\n");break;
				case 1 : case 2 : case 3 : case 4 :
					
					System.out.print("검색 : ");
					String keyword = sc.next();
					
					List<Manager> bookList = service.searchBook(menuNum, keyword);
					
					if(bookList.isEmpty()) {
						System.out.println("\n조회 결과가 없습니다.\n");
					} else {
						
						System.out.println("-------------------------------------------------------------------------------------");
						System.out.printf("%s   %3s   %10s   %10s   %9s   %11s   ",
								"도서 번호", "도서 제목", "작가", "분류", "등록일", "대여 횟수");
						System.out.println("\n-------------------------------------------------------------------------------------");
							
						for(Manager mg : bookList) {
							
							System.out.printf("%5d   %10s   %s   %s   %5s   %7d   \n",
									mg.getBookNo(), mg.getBookName(), mg.getBookWriter(),
									mg.getBookGr(), mg.getEnrollDate(), mg.getBookRcount());
							
						}
						
					}
					
					return;
					
					//break;
				
				default : System.out.println("\n메뉴에 작성된 번호를 입력해주세요.\n");
				
				}
				
			} catch(InputMismatchException e) {
				System.out.println("\n<입력 형식이 올바르지 않습니다.>\n");
				sc.nextLine();
				
			} catch(Exception e) {
				System.out.println("\n<도서 검색 중 예외 발생>\n");
				e.printStackTrace();
				break;
			}
		} while (menuNum != 0);
		
	}



	/**
	 * 도서 수정
	 */
	public void updateBook() {
		
		System.out.println("\n[도서 수정]\n");
		
		try {
			
			int bookNo = 0;
			
			while(true) {
				
				System.out.print("도서 번호 : ");
				bookNo = sc.nextInt();
				sc.nextLine();
				
				int result = service.checkBook(bookNo);
				
				if(result > 0) {
					break;
				} else {
					System.out.println("\n해당 도서가 없습니다.\n");
				}
			}
			
			System.out.print("수정할 제목 : ");
			String bookName = sc.nextLine();
			
			
			System.out.print("수정할 책의 작가 : ");
			String bookWriter = sc.nextLine();
	
			
			System.out.print("수정할 분류(장르) : ");
			String bookGr = sc.nextLine();
	
			
			Member book = new Member();
			book.setBookNo(bookNo);
			book.setBookName(bookName);
			book.setBookWriter(bookWriter);
			book.setBookGr(bookGr);
			
		
			int result = service.updateBook(book);
		
			if(result > 0) {
				System.out.println("\n[수정 되었습니다.]\n");
			} else {
				System.out.println("\n[수정 실패]\n");
			}
		
		} catch(Exception e) {
			System.out.println("\n<도서 수정 중 예외 발생>\n");
		}
		
	}



	/**
	 * 대여중인 도서/회원
	 */
	public void rentBookMember() {
		
		System.out.println("\n[대여 중인 도서/회원]\n");
		
		try {
			
			List<Manager> rentList = service.rentBookMember();
			
			if(rentList.isEmpty()) {
				System.out.println("\n조회된 도서/회원이 없습니다.\n");
				
			} else {
				
				System.out.println("-------------------------------------------------------------------------------------");
				System.out.printf("%s     %s     %s     %s     %s","도서번호","도서분류","도서이름","회원번호","회원이름");
				System.out.println("\n------------------------------------------------------------------------------------");
				
				for(Manager mg : rentList) {
					
					System.out.printf("%5d     %7s     %7s     %3d     %5s     \n",
							mg.getBookNo(), mg.getBookGr(), mg.getBookName(),
							mg.getMemberNo(), mg.getMemberName());
					
				}
				
			}
		
		} catch(Exception e) {
			System.out.println("\n<대여중인 도서/회원 조회 중 예외 발생>\n");
			e.printStackTrace();
		}
		
	}

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
