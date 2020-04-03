package jae.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import jae.board.dbcp.Context;
import jae.board.model.BoardVO;
import jae.board.model.MemberVO;

public class BoardDAO {

	public MemberVO login(String id, String passwd) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MemberVO memberDTO = null;
		String sql = null;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "select * from member ";
			sql += "where id=? and passwd=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, passwd);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				id = resultSet.getString("id");
				passwd = resultSet.getString("passwd");
				int age = resultSet.getInt("age");
				String email = resultSet.getString("email");
				memberDTO = new MemberVO();
				memberDTO.setName(name);
				memberDTO.setId(id);
				memberDTO.setPasswd(passwd);
				memberDTO.setAge(age);
				memberDTO.setEmail(email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberDTO;
	}

	public void writeBoard(BoardVO boardVO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "insert into board ";
			sql += " values(board_seq.nextval,?,?,?,?,?) ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, boardVO.getId());
			preparedStatement.setString(2, boardVO.getPasswd());
			preparedStatement.setString(3, boardVO.getSubject());
			preparedStatement.setString(4, boardVO.getContent());
			preparedStatement.setInt(5, 0);
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				System.out.println("글을 등록하였습니다 .");
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateViewcount(int num) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "update board set viewcount = viewcount + 1 ";
			sql += " where num = ? ";
			System.out.println(num);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<BoardVO> pagingSelect(int page) {
		ArrayList<BoardVO> arrayList = new ArrayList<BoardVO>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "select * from ( ";
			sql += "select rownum rownm, num, id, passwd";
			sql += ", subject, content, viewcount";
			sql += " from board)";
			sql += " where rownm between (((?-1)*5) + 1) and (((?-1)*5) + 5)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, page);
			preparedStatement.setInt(2, page);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int num = resultSet.getInt("num");
				String id = resultSet.getString("id");
				String passwd = resultSet.getString("passwd");
				String subject = resultSet.getString("subject");
				String content = resultSet.getString("content");
				int viewcount = resultSet.getInt("viewcount");
				BoardVO boardVO = new BoardVO();
				boardVO.setNum(num);
				boardVO.setId(id);
				boardVO.setPasswd(passwd);
				boardVO.setSubject(subject);
				boardVO.setContent(content);
				boardVO.setViewcount(viewcount);
				arrayList.add(boardVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrayList;

	}

	public int totalCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;
		int num = 0;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "select count(*) cnt from board";
			preparedStatement = connection.prepareStatement(sql);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				num = resultSet.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}

	public int nameCount(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;
		int num = 0;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "select count(*) cnt from (select rownum rownm, b.num, b.id, b.passwd, b.subject, b.content, m.name";
			sql += " from board b, member m";
			sql += " where b.id=m.id and b.id=?) ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);

			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				num = resultSet.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}

	public ArrayList<BoardVO> IDfind(String id, int page) {
		ArrayList<BoardVO> arrayList = new ArrayList<BoardVO>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "select * from (select rownum rownm, b.num, b.id, b.passwd, b.subject, b.content, m.name, b.viewcount";
			sql += " from board b, member m ";
			sql += " where b.id=m.id and b.id=?)";
			sql += " where rownm between (((?-1)*5) + 1) and (((?-1)*5) + 5)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setInt(2, page);
			preparedStatement.setInt(3, page);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int num = resultSet.getInt("num");
				String name = resultSet.getString("id");
				String passwd = resultSet.getString("passwd");
				String subject = resultSet.getString("subject");
				String content = resultSet.getString("content");
				int viewcount = resultSet.getInt("viewcount");
				BoardVO boardVO = new BoardVO();
				boardVO.setNum(num);
				boardVO.setId(name);
				boardVO.setPasswd(passwd);
				boardVO.setSubject(subject);
				boardVO.setContent(content);
				boardVO.setViewcount(viewcount);
				arrayList.add(boardVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrayList;
	}

	public int updateBoard(int num, String subject, String content) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		Context context = new Context();
		int count = 0;
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "update board set subject = ?, content = ? ";
			sql += " where num = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, subject);
			preparedStatement.setString(2, content);
			preparedStatement.setInt(3, num);
			count = preparedStatement.executeUpdate();
			if (count > 0) {
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public BoardVO upSelect(int num) {
		BoardVO boardVO = new BoardVO();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "select * from board ";
			sql += " where num = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int number = resultSet.getInt("num");
				String id = resultSet.getString("id");
				String passwd = resultSet.getString("passwd");
				String subject = resultSet.getString("subject");
				String content = resultSet.getString("content");
				int viewcount = resultSet.getInt("viewcount");
				boardVO.setNum(number);
				boardVO.setId(id);
				boardVO.setPasswd(passwd);
				boardVO.setSubject(subject);
				boardVO.setContent(content);
				boardVO.setViewcount(viewcount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return boardVO;

	}

	public int deleteBoard(int num) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		Context context = new Context();
		int count = 0;
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "delete from board";
			sql += " where num = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			count = preparedStatement.executeUpdate();
			if (count > 0) {
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

}
