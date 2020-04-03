package jae.board.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jae.board.dao.BoardDAO;
import jae.board.find.FindID;
import jae.board.model.MemberVO;
import jae.board.service.BoardPagingSelect;
import jae.board.service.BoardWrite;

public class BoardMain {

	public static void boardmain(MemberVO memberVO) throws IOException {
		boolean isStop = true;
		boolean isLogin = false;
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		BoardDAO boardDAO = new BoardDAO();

		do {

			if (memberVO == null) {

				System.out.println();
				System.out.println("게시판 로그인 페이지");
				System.out.print("id: ");
				String id = bufferedReader.readLine();
				System.out.print("비밀번호: ");
				String passwd = bufferedReader.readLine();
				memberVO = boardDAO.login(id, passwd);

				if (memberVO == null) {
					System.out.println("아이디와 비밀번호가 존재하지 않습니다 .");
					System.out.println("회원 가입을 해 주십시오 .");
					System.out.print(" 회원 가입을 하시겠습니까?(y/n) : ");
					String choice = bufferedReader.readLine();
					switch (choice) {
					case "y":
						MemberMain.Membermain();
						break;
					case "n":
						LoginMain.main(null);

						break;
					default:
						System.out.println("잘못 입력하셨습니다 .");
					}
					return;
				} else {
					System.out.println("로그인이 성공하였습니다 ");
				}
			}

			System.out.println();

			System.out.println("== 게시판 페이지==");
			System.out.print("1. 등록");
			System.out.print("\t2. 목록 ");
			System.out.print("\t3. 검색 (아이디) ");
			System.out.print("\t4. 회원관리");
			System.out.println("\t5. 게시판 종료 ");
			System.out.println(" 번호를 선택하세요.");
			System.out.print(" 번호 입력: ");
			String menu = null;
			menu = bufferedReader.readLine();
			switch (menu) {
			case "1":
				BoardWrite writeArticle = new BoardWrite();
				writeArticle.execute(bufferedReader);

				break;
			case "2":
				BoardPagingSelect boardPagingSelect = new BoardPagingSelect();
				boardPagingSelect.execute(bufferedReader);

				break;
			case "3":
				FindID findID = new FindID();
				findID.execute(bufferedReader);

				break;
			case "4":
				MemberMain.Membermain();

				break;
			case "5":
				isStop = false;

				break;

			}

		} while (isStop);
	}

}
