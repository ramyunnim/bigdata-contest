package jae.board.dbcp;

import org.apache.commons.dbcp2.BasicDataSource;

public class Context {

	public BasicDataSource basicDataSource;

	// 초기화
	public Context() {

		basicDataSource = new BasicDataSource();
		// class.forName 역할
		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicDataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		basicDataSource.setUsername("jack");
		basicDataSource.setPassword("1234");
		basicDataSource.setInitialSize(4);
		basicDataSource.setMaxIdle(100);
		basicDataSource.setMinIdle(5);
	}

}
