package jae.board.service;

import java.io.BufferedReader;
import java.io.IOException;

import jae.board.control.BoardAction;
import jae.board.dao.MemberDAO;
import jae.board.model.MemberVO;
import jae.board.view.BoardMain;
import jae.board.view.LoginMain;

public class MemberLogin implements BoardAction {
	@Override
	public void execute(BufferedReader bufferedReader) throws IOException {
		boolean isStop = false;
		do {
			System.out.println();
			System.out.println("==로그인 페이지==");
			System.out.print("아이디: ");
			String id = bufferedReader.readLine();
			System.out.print("비밀번호: ");
			String passwd = bufferedReader.readLine();
			MemberDAO memberDAO = new MemberDAO();
			MemberVO memberVO = memberDAO.login(id, passwd);
			if (memberVO == null) {
				System.out.println("아이디와 비밀번호가 존재하지 않습니다.");
				System.out.println("회원 가입을 해 주십이오.");
				System.out.print("회원 가입을 하시겠습니까? (y/n): ");
				String choice = bufferedReader.readLine();
				switch (choice) {
				case "y":
					MemberJoin.join(bufferedReader);
					break;
				case "n":
					LoginMain.main(null);
					break;
				default:
					System.out.println("잘못 입력하셨습니다.");
				}
			} else {
				System.out.println("로그인이 성공하였습니다.");
				BoardMain.boardmain(memberVO);
				isStop = true;
			}

		} while (!isStop);

	}
}
