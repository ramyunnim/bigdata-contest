package jae.board.find;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import jae.board.dao.BoardDAO;
import jae.board.model.BoardVO;
import jae.board.service.BoardDelete;
import jae.board.service.BoardUpdate;

public class FindID {

	public void execute(BufferedReader bufferedReader) throws IOException {

		String id = null;
		int page = 1;
		boolean isSelect = false;
		boolean isStop = true;
		boolean moFlag = true;
		int count = 0;
		int totalPage = 0;
		ArrayList<BoardVO> arrayList = new ArrayList<BoardVO>();
		BoardDAO boardDAO = new BoardDAO();
		BoardVO boardVOMO = new BoardVO();

		System.out.println("검색할 아이디를 입력하세요 .");
		System.out.print("아이디 : ");
		id = bufferedReader.readLine();
		count = boardDAO.nameCount(id);

		if (count % 5 != 0) {
			totalPage = (count / 5) + 1;
		} else {
			totalPage = (count / 5);
		}

		do {

			if (isSelect) {
				System.out.println("검색할 페이지를 입력하세요 .");
				System.out.print("페이지 : ");
				page = Integer.parseInt(bufferedReader.readLine());
			}

			arrayList = boardDAO.IDfind(id, page);

			for (BoardVO boardVO : arrayList) {
				System.out.print("게시 번호: " + boardVO.getNum() + "\t");
				System.out.print("작성자: " + boardVO.getId() + "\t");
				System.out.print("제목: " + boardVO.getSubject() + "\t");
				System.out.print("조회수: " + boardVO.getViewcount() + "\t");
				System.out.println();
			}
			System.out.println(page + " / " + totalPage);
			System.out.println();

			System.out.println("1. 게시글 보기 2. 페이지 검색 3. 페이지 검색 종료");
			System.out.print("메뉴 선택: ");
			int search = Integer.parseInt(bufferedReader.readLine());

			switch (search) {
			case 1:
				boolean numFlag = false;
				isSelect = false;
				System.out.println("보고 싶은 게시글 번호를 선택하세요.");
				System.out.print("게시글 번호: ");
				int num = Integer.parseInt(bufferedReader.readLine());

				for (BoardVO boardVO : arrayList) {
					if (num == boardVO.getNum()) {
						System.out.println("-----------------------------------------");
						System.out.print("게시 번호: " + boardVO.getNum() + "\t");
						System.out.println("|| 작성자: " + boardVO.getId() + "\t");
						System.out.println("-----------------------------------------");
						System.out.println("제목: " + boardVO.getSubject() + "\t");
						System.out.println("-----------------------------------------");
						System.out.println("글 내용: " + boardVO.getContent() + "\t");
						System.out.println("-----------------------------------------");
						System.out.println("조회수: " + boardVO.getViewcount() + "\t");
						System.out.println();
						numFlag = true;
					}
				}
				if (numFlag)
					boardDAO.updateViewcount(num);

				if (!numFlag)
					System.out.println("게시번호를 잘못 입력하였습니다.");

				do {

					System.out.println("1. 수정 2. 삭제 3. 나가기");
					System.out.print("메뉴 선택: ");
					int modify = Integer.parseInt(bufferedReader.readLine());

					switch (modify) {
					case 1:
						BoardUpdate boardUpdate = new BoardUpdate(boardVOMO);
						boardUpdate.execute(bufferedReader);

						break;

					case 2:
						BoardDelete boardDelete = new BoardDelete(boardVOMO);
						boardDelete.execute(bufferedReader);
						moFlag = false;

						break;

					case 3:
						moFlag = false;

						break;

					default:
						break;
					}

				} while (moFlag);

				break;

			case 2:
				isSelect = true;
				break;

			case 3:
				isStop = false;
				break;

			default:
				System.out.println("잘못입력하였습니다. 검색 종료하겠습니다.");
				isStop = false;
				break;
			}

		} while (isStop);

	}

}
