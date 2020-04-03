package jae.board.service;

import java.io.BufferedReader;
import java.io.IOException;

import jae.board.control.BoardAction;
import jae.board.dao.MemberDAO;
import jae.board.model.MemberVO;
import jae.board.view.MemberMain;

public class MemberUpdate implements BoardAction {

	@Override
	public void execute(BufferedReader bufferedReader) throws IOException {
		System.out.println();
		System.out.println("==회원정보 수정 페이지==");
		System.out.println("회원 정보를 수정합니다.");
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
			System.out.println("수정할 회원 정보입니다.");
			System.out.println("패스워드:" + memberVO.getPasswd());
			System.out.print("패스워드 수정: ");
			passwd = bufferedReader.readLine();
			System.out.println("이름: " + memberVO.getName());
			System.out.print("이름 수정: ");
			String name = bufferedReader.readLine();
			System.out.println("나이: " + memberVO.getAge());
			System.out.print("나이 수정: ");
			int age = Integer.parseInt(bufferedReader.readLine());
			System.out.println("이메일: " + memberVO.getEmail());
			System.out.print("이메일 수정: ");
			String email = bufferedReader.readLine();
			memberDAO.update(id, passwd, name, age, email);
		}

	}

}
