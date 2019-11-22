package jp.ucs.logic;

import java.util.Map;

import jp.ucs.dao.LicenseDAO;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム クラス名 ：licenseLogic 処理概要 :社員の資格を検索する。 プロジェクト名：HrsmUcs 作成者 ：高原優
 * 作成日付：2019年11月19日(火)
 */

public class licenseLogic {
	public Map<String, String> licenseExecute() throws HrsmUcsDBException {

		// LicenseDAoのインスタンスの生成
		LicenseDAO license = new LicenseDAO();

		// Mapに値を格納
		Map<String, String> licenseMap = license.LicenseFindPart();

		// licenseMapを返す
		return licenseMap;
	}

}
