package jae.board.service;

import java.io.BufferedReader;
import java.io.IOException;

import jae.board.control.BoardAction;
import jae.board.dao.MemberDAO;
import jae.board.model.MemberVO;
import jae.board.view.MemberMain;

public class MemberDelete implements BoardAction {

	@Override
	public void execute(BufferedReader bufferedReader) throws IOException {
		System.out.println();
		System.out.println("==회원정보 탈퇴 페이지==");
		System.out.println("회원에서 탈되합니다.");
		System.out.print("아이디: ");
		String id = bufferedReader.readLine();
		System.out.print("비밀번호: ");
		String passwd = bufferedReader.readLine();
		MemberDAO memberDAO = new MemberDAO();
		MemberVO memberVO = memberDAO.login(id, passwd);
		if (memberVO == null) {
			System.out.println("아이디와 비밀번호가 존재하지 않습니다.");
			MemberMain.Membermain();
		} else {
			if (memberVO.getId().equals(id)) {
				System.out.println("회원에서 탈퇴하시겠습니까?(y/n): ");
				String choice = bufferedReader.readLine();
				switch (choice) {
				case "y":
					memberDAO.delete(id);
					break;
				case "n":
					System.out.println("프로그램이 종료됩니다.");
					break;
				default:
					System.out.println("잘못 입력하셧습니다.");
				}
			}
		}
	}

}
