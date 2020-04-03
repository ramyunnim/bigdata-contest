package jae.board.service;

import java.io.BufferedReader;
import java.io.IOException;

import jae.board.dao.MemberDAO;
import jae.board.model.MemberVO;

public class MemberJoin {

	public static void join(BufferedReader bufferedReader) throws IOException {
		MemberDAO memberDAO = new MemberDAO();
		String id = null;
		System.out.println();
		System.out.println("==회원 정보 입력 페이지==");
		do {
			System.out.print("아이디: ");
			id = bufferedReader.readLine();
		} while (!memberDAO.idUnique(id));
		System.out.print("비밀번호: ");
		String passwd = bufferedReader.readLine();
		System.out.print("이름: ");
		String name = bufferedReader.readLine();
		System.out.print("나이: ");
		int age = Integer.parseInt((bufferedReader.readLine()));
		System.out.print("이메일: ");
		String email = bufferedReader.readLine();
		MemberVO memberVO = new MemberVO();
		memberVO.setId(id);
		memberVO.setPasswd(passwd);
		memberVO.setName(name);
		memberVO.setAge(age);
		memberVO.setEmail(email);
		memberDAO.join(memberVO);

	}

}
