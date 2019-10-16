package jp.ucs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import jp.ucs.bean.LicenseBean;

public class LicenseDAO extends BaseDAO{
	public Map<String, String> licenseFindAll(){


	try (Connection conn = DriverManager.getConnection(DB_URL, DB_ID, PWD)) {

		StringBuilder sb = new StringBuilder();
		sb.append("select license_id,license_name");
		sb.append("from license;");
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();

		sb.append("insert into have_license(property_id ,serial_id,license_id,license_date)");
		sb.append("values('?','?','?','?');");

		//DBから情報を取り出し、インスタンスに格納
		while (rs.next()) {
			String propertyid = rs.getString("property_id");
			String serialid = rs.getString("serial_id");
			String licenseId = rs.getString("license_id");
			String licenseDate = rs.getString("license_date");

			LicenseBean liBean = new LicenseBean(licenseId,licenseDate);



		}

	} catch (SQLException e) {
		e.printStackTrace();

	}
	return null;

}

}
