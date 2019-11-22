package jp.ucs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.bean.LicenseBean;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム クラス名 ：insertLicensetDAO 処理概要 ：資格登録画面のDAO
 * プロジェクト名：HrsmUcs(資格登録画面) 作成者 ：高原 優 作成日付：2019/11/19(火)
 */

public class insertLicenseDAO extends BaseDAO {

	/**
	 * メソッド名:registerEmp 説明: 資格情報を登録する。
	 *
	 * @param: registerLicense
	 * @return true
	 */

	public Map<String, String> licenseFindPart(EmployeeBean InfoEmp) throws HrsmUcsDBException {

		// LicenseBean型のリストを作成
		// ArrayList<LicenseBean> licenseList = new ArrayList<>();

		// Map型のインスタンスを生成
		Map<String, String> license = new HashMap<>();

		// DBの接続
		try (Connection conn = getConnection()) {
			StringBuilder sb = new StringBuilder();
			sb.append("select license_id ,license_name ");
			sb.append("from license license where not exists( ");
			sb.append("select * from have_license where ");
			sb.append("property_id = '");
			sb.append(InfoEmp.getPropertyId());
			sb.append("' and serial_id = '");
			sb.append(InfoEmp.getSerialId());
			sb.append("' and license_id = license.license_id);");

			// SQLの実行
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();

			// DBから情報を取り出し、インスタンスに格納
			while (rs.next()) {
				String licenseId = rs.getString("license_id");
				String licenseName = rs.getString("license_name");
				// LicenseBean license = new LicenseBean(licenseId,
				// licenseName);
				// licenseList.add(license);
				license.put(licenseId, licenseName);
			}

		} catch (SQLException e) {
			throw new HrsmUcsDBException();
		}

		// 追加出来たらlicenseListを返す
		return license;
	}

	public LicenseBean registerLicense(LicenseBean LicenseInfo) throws HrsmUcsDBException {
		// DB接続
		try (Connection conn = getConnection()) {
			// SQL文の準備
			// have_licenseテーブルに資格情報を追加
			StringBuilder sb = new StringBuilder();
			sb.append("insert into ");
			sb.append("have_license ( ");
			sb.append("property_id,serial_id,license_id,license_date ) ");
			sb.append("values( ");
			sb.append("'");
			sb.append(LicenseInfo.getPropertyId());
			sb.append("' , ");
			sb.append("'");
			sb.append(LicenseInfo.getSerialId());
			sb.append("' , ");
			sb.append("'");
			sb.append(LicenseInfo.getLicenseId());
			sb.append("' , ");
			sb.append("'");
			sb.append(LicenseInfo.getLicenseDate());
			sb.append("'); ");

			// insert文の実行
			PreparedStatement pStmt = conn.prepareStatement(sb.toString());

			// データベースに登録
			pStmt.executeUpdate();

			// propertyIdとserialIdを結合したものをempIdにする
			String empId = LicenseInfo.getPropertyId() + LicenseInfo.getSerialId();

			LicenseInfo.setEmpId(empId);

			// 登録出来たらEmpInfoを返す
			return LicenseInfo;
		}

		// DB接続エラーがでたら、HrsmUcsDBExceptionをスローする
		catch (SQLException e) {
			throw new HrsmUcsDBException();
		}
	}
}
