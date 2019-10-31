package jp.ucs.logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.constants.MessageConstants;
import jp.ucs.dao.EmpInsertDAO;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム クラス名 ：RegisterLogic 処理概要 :社員の登録処理をする。 プロジェクト名：Hrsm 作成者 ：小西香菜子
 * 作成日付：2019年9月30日
 */

public class RegisterLogic {

	public boolean registerExecute(EmployeeBean empbean) throws HrsmUcsDBException {

		// select
		EmpInsertDAO empinDAO = new EmpInsertDAO();
		String maxId = empinDAO.searchEmp(empbean);

		// 最大値に1加算
		int intMaxId = Integer.parseInt(maxId) + 1;
		String serialId = String.format("%04d", Integer.parseInt(maxId));

		// propertyId,serialIdをbeanに追加
		String propertyId = empbean.getEmpId().substring(0, 4);
		EmployeeBean employeeBean = new EmployeeBean(propertyId, serialId);

		// 追加

		if (empinDAO.registerEmp(employeeBean)) {
			return true;
		}

		return false;
	}

	public List<String> checkInputData(EmployeeBean empbean) {

		// エラーメッセージリスト
		List<String> errorMsgList = new ArrayList<>();

		// 名前の入力チェック

		byte[] bytes = empbean.getEmpName().getBytes();
		if (empbean.getEmpName() == null) {

			errorMsgList.add(MessageConstants.REGEMP_ERR01);

		}
		if (empbean.getEmpName().length() != bytes.length) {

			// 60字以上か
			if (empbean.getEmpName().length() != bytes.length || empbean.getEmpName().length() > 60) {
				errorMsgList.add(MessageConstants.REGEMP_ERR06);
			}

			// ふりがなの入力チェック
			if (empbean.getRuby() == null || empbean.getRuby().length() == 0) {

				errorMsgList.add(MessageConstants.REGEMP_ERR02);

			}
			if (!empbean.getRuby().matches("^[\\u3040-\\u309F]+$")) {

				errorMsgList.add(MessageConstants.REGEMP_ERR10);

			}
			if (empbean.getRuby().length() > 80) {

				errorMsgList.add(MessageConstants.REGEMP_ERR07);
			}

			// 部門の入力チェック
			if (empbean.getDept() == null) {

				errorMsgList.add(MessageConstants.REGEMP_ERR03);

				// パスワードの入力チェック
				if (empbean.getPass() == null || empbean.getPass().length() == 0) {

					errorMsgList.add(MessageConstants.REGEMP_ERR04);

				} else if (empbean.getPass().length() < 8 || empbean.getPass().length() > 16) {

					errorMsgList.add(MessageConstants.REGEMP_ERR08);

				}

				// 入社年月日の入力チェック
				if (empbean.getEntryDate() == null || empbean.getEntryDate().length() == 0) {

					errorMsgList.add(MessageConstants.REGEMP_ERR05);

				}
				if (empbean.getEntryDate().length() != 10) {

					errorMsgList.add(MessageConstants.REGEMP_ERR09);

				} else {

				}
				try {

					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					df.setLenient(false);
					df.parse(empbean.getEntryDate());

					// フォーマット誤り
				} catch (ParseException | java.text.ParseException e) {

					errorMsgList.add(MessageConstants.REGEMP_ERR11);

				}
			}
		}
		return errorMsgList;
	}
}
