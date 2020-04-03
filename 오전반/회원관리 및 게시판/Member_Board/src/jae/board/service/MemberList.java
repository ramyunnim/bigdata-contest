package jae.board.service;

import java.io.BufferedReader;
import java.io.IOException;

import jae.board.control.BoardAction;
import jae.board.dao.MemberDAO;
import jae.board.model.MemberVO;
import jae.board.view.MemberMain;

public class MemberList implements BoardAction {
	@Override
	public void execute(BufferedReader bufferedReader) throws IOException {
		System.out.println();
		System.out.println("==회원정보 확인 페이지==");
		System.out.print("아이디: ");
		String id = bufferedReader.readLine();
		System.out.print("비밀번호: ");
		String passwd = bufferedReader.readLine();
		MemberDAO memberDAO = new MemberDAO();
		MemberVO memberVO = memberDAO.login(id, passwd);
		// System.out.println(memberVO.toString());
		if (memberVO == null) {
			System.out.println("아이디와 비밀번호가 존재하지 않습니다.");
			MemberMain.Membermain();
		} else {
			if (memberVO.getId().equals(id)) {
				String id1 = memberVO.getId();
				String passwd1 = memberVO.getPasswd();
				String name = memberVO.getName();
				int age = memberVO.getAge();
				String email = memberVO.getEmail();
				System.out.println("회원 정보입니다.");
				System.out.print("1.아이디: " + id1 + " ");
				System.out.print("2.비밀번호: " + passwd1 + " ");
				System.out.print("2.이름: " + name + " ");
				System.out.print("3.나이: " + age + " ");
				System.out.print("4.이메일: " + email + " ");
				System.out.println();

			}

		}

	}

}
