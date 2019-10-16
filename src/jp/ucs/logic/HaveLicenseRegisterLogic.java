package jp.ucs.logic;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.dao.HaveLicenseDAO;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム
 * クラス名  ：HaveLicenseRegisterLogic
 * 処理概要   :取得資格の登録処理をする。
 * プロジェクト名：HrsmUcs
 * 作成者    ：小西香菜子
 * 作成日付：2019年10月10日(木)
 */


public class HaveLicenseRegisterLogic {

    public String haveLicenseExecute(EmployeeBean empbean) throws HrsmUcsDBException {

        HaveLicenseDAO haveLi = new HaveLicenseDAO();
        haveLi.haveLicenseRegister(empbean);

        return haveLi.haveLicenseRegister(empbean);
    }

}
