package jae.board.service;

import java.io.BufferedReader;
import java.io.IOException;

import jae.board.control.SJMAction;
import jae.board.dao.BoardDAO;
import jae.board.model.BoardVO;

public class BoardUpdate implements SJMAction {
	BoardVO boardVO = new BoardVO();
	BoardDAO boardDAO = new BoardDAO();

	public BoardUpdate(BoardVO boardVO) {
		this.boardVO = boardVO;
		System.out.println(4);
	}

	@Override
	public void execute(BufferedReader bufferedReader) throws IOException {

		System.out.println("비밀번호를 입력하세요");
		System.out.print("비밀번호: ");
		String passwd = bufferedReader.readLine();

		if (!passwd.equals(boardVO.getPasswd())) {
			System.out.println("패스워드가 틀렸습니다.");
		}

		System.out.println("수정할 글 제목을 입력하세요.");
		System.out.print("수정할 글 제목: ");
		String subject = bufferedReader.readLine();

		System.out.println("수정할 글 내용을 입력하세요.");
		System.out.print("수정할 글 내용: ");
		String content = bufferedReader.readLine();

		int update = boardDAO.updateBoard(boardVO.getNum(), subject, content);

		if (update == 1) {

			BoardVO boardVOup = new BoardVO();
			boardVOup = boardDAO.upSelect(boardVO.getNum());

			System.out.println("-----------------------------------------");
			System.out.print("게시 번호: " + boardVOup.getNum() + "\t");
			System.out.println("|| 작성자: " + boardVOup.getId() + "\t");
			System.out.println("-----------------------------------------");
			System.out.println("제목: " + boardVOup.getSubject() + "\t");
			System.out.println("-----------------------------------------");
			System.out.println("글 내용: " + boardVOup.getContent() + "\t");
			System.out.println("-----------------------------------------");
			System.out.println("조회수: " + boardVOup.getViewcount() + "\t");
			System.out.println();

		}

	}

}
