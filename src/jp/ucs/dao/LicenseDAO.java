package jp.ucs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム クラス名 ：licenseDAO 処理概要 ：資格登録画面のDAO プロジェクト名：HrsmUcs(資格登録) 作成者
 * ：高原 優 作成日付：2019/11/19(火)
 */

public class LicenseDAO extends BaseDAO {

	public Map<String, String> LicenseFindPart() throws HrsmUcsDBException {

		// Map型のlicenseを生成
		Map<String, String> license = new HashMap<>();

		// DBに接続
		try (Connection conn = getConnection()) {
			// SQL文の実行
			// licenseテーブルからデータの取得
			StringBuilder sb = new StringBuilder();
			sb.append("Select license_id , license_name ");
			sb.append("from license ");
			sb.append("where exists ");
			sb.append("(select license_id from have_license ");
			sb.append("where license.license_id = have_license.license_id )");

			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();

			// DBから情報を取り出し、Mapに格納
			while (rs.next()) {
				String licenseId = rs.getString("license_id");
				String licenseName = rs.getString("license_name");
				license.put(licenseId, licenseName);
			}

			// エラー処理
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return license;
	}
}