package jp.ucs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {

	protected static final String DRIVER_NAME = "org.postgresql.Driver";
	protected static final String DB_URL = "jdbc:postgresql:hrsmucs";
	protected static final String DB_ID = "postgres";
	protected static final String PWD = "HrsmUcs@2019";

	protected static Connection getConnection() throws SQLException {
		try {
			// postgreSQLのJDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			// JDBCドライバが見つからない場合
			e.printStackTrace();
		}

		return DriverManager.getConnection(DB_URL, DB_ID, PWD);
	}
}
