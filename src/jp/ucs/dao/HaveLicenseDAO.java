package jp.ucs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.bean.LicenseBean;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム
 * クラス名  ：HaveLicenseDAO
 * 処理概要   :社員を登録する。
 * プロジェクト名：Hrsm
 * 作成者    ：小西香菜子
 * 作成日付：2019年10月10日
 */

public class HaveLicenseDAO extends BaseDAO{
	public String haveLicenseRegister (EmployeeBean empbean) throws HrsmUcsDBException{

		List<EmployeeBean>empList = new ArrayList<>();
		List<LicenseBean>licenseList = new ArrayList<>();

		// 社員IDの初期値を設定
		String empId = "";
		String serialId = "";

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_ID, PWD)) {

			try {
				Class.forName(DRIVER_NAME);
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
			// 資格名を取得
			StringBuilder sb = new StringBuilder();
			sb.append("select license_id,license_name from license");
			sb.append("order by license_id asc;");

			sb.append("where license_id like '%1%';");
			sb.append("group by license_id;");

			PreparedStatement pStmt = conn.prepareStatement(sb.toString());

			ResultSet rs = pStmt.executeQuery();


			while(rs.next()) {
				String propertyId = rs.getString("property_id");
				String serialId1 = rs.getString("serial_id");
				String licenseId = rs.getString("license_id");
				String licenseName = rs.getString("license_name");
				EmployeeBean employeeBean = new EmployeeBean(propertyId,serialId);
				LicenseBean licenseBean = new LicenseBean(licenseId,licenseName);
				empList.add(employeeBean);
				licenseList.add(licenseBean);

			}

			//have_licenseテーブルに新しいレコードとして挿入
			sb.append("insert into have_license(property_id,serial_id,license_id,license_date)");
			sb.append("values('?','?','?','?');");

			pStmt = conn.prepareStatement(sb.toString());


			int result = pStmt.executeUpdate();

			if(result != 1){
				return null;
			}


		} catch (SQLException e) {

			e.printStackTrace();
		}
		return empId;
	}
}
