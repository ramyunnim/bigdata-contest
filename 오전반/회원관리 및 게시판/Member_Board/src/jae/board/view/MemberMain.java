package jae.board.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jae.board.service.MemberDelete;
import jae.board.service.MemberJoin;
import jae.board.service.MemberList;
import jae.board.service.MemberUpdate;

public class MemberMain {
	public static void Membermain() throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		boolean isStop = false;
		do {
			System.out.println();
			System.out.println("-----회원 관리-----");
			System.out.print("1.회원가입");
			System.out.print("\t   2.회원정보 확인");
			System.out.print("\t 3.회원정보 수정");
			System.out.print("\t 4.회원정보 삭제");
			System.out.print("\t 5.종료");
			System.out.println();
			System.out.println("번호를 선택하세요.");
			System.out.print("번호 입력: ");
			String menu = bufferedReader.readLine();
			switch (menu) {
			case "1":
				MemberJoin.join(bufferedReader);
				break;
			case "2":
				MemberList memberList = new MemberList();
				memberList.execute(bufferedReader);
				break;
			case "3":
				MemberUpdate memberUpdate = new MemberUpdate();
				memberUpdate.execute(bufferedReader);
				break;
			case "4":
				MemberDelete memberDelete = new MemberDelete();
				memberDelete.execute(bufferedReader);
			case "5":
				isStop = true;
				break;
			default:
				System.out.println("잘못 입력하셧습니다.");

			}

		} while (!isStop);

	}

}
