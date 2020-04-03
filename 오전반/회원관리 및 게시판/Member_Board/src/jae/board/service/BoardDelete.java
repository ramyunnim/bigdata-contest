package jae.board.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import jae.board.control.SJMAction;
import jae.board.dao.BoardDAO;
import jae.board.model.BoardVO;

public class BoardDelete implements SJMAction {

	public String flag = null;
	public int page = 1;
	BoardVO boardVO = new BoardVO();
	BoardDAO boardDAO = new BoardDAO();
	ArrayList<BoardVO> arrayList = new ArrayList<BoardVO>();

	public BoardDelete(BoardVO boardVO) {
		this.boardVO = boardVO;
	}

	@Override
	public void execute(BufferedReader bufferedReader) throws IOException {

		System.out.println("비밀번호를 입력하세요");
		System.out.print("비밀번호: ");
		String passwd = bufferedReader.readLine();

		if (!passwd.equals(boardVO.getPasswd())) {
			System.out.println("패스워드가 틀렸습니다.");
		}

		int delete = boardDAO.deleteBoard(boardVO.getNum());

		if (delete == 1) {
			System.out.println("삭제가 완료되었습니다.");

		}

	}

}
