package jae.board.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jae.board.model.MemberVO;
import jae.board.service.MemberLogin;

public class LoginMain {
	public static void main(String[] args) throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		boolean isStop = true;

		do {
			System.out.println();
			System.out.println("======메인화면======");
			System.out.print("1.로그인");
			System.out.print("\t2.회원가입");
			System.out.print("\t  3.게시판");
			System.out.print("\t4.종료");
			System.out.println();
			System.out.println("번호를 선택하세요.");
			System.out.print("번호 입력: ");
			String menu = null;
			menu = bufferedReader.readLine();
			switch (menu) {
			case "1":
				MemberLogin memberLogin = new MemberLogin();
				memberLogin.execute(bufferedReader);
				break;
			case "2":
				MemberMain.Membermain();
				break;
			case "3":
				BoardMain.boardmain(null);
				break;
			case "4":
				System.exit(0);
				break;
			}

		} while (isStop);

	}

}
