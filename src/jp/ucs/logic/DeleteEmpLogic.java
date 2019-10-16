package jp.ucs.logic;

import java.util.List;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.dao.DeleteEmpDAO;
import jp.ucs.exception.HrsmUcsDBException;

public class DeleteEmpLogic {

	 /**
     * メソッド名：deleteEmpLogic
     * 説明：削除選択された社員を社員表から削除するDAOを呼び出すメソッド
     * @param  deleteEmpList :削除選択された社員のリスト
     * @return true   :削除が成功したときに返す
     */
    public boolean deleteExecute(List<EmployeeBean> deleteEmpList) throws HrsmUcsDBException{

        //インスタンスの生成
        DeleteEmpDAO deleteEmpDAO = new DeleteEmpDAO();

        //削除選択された社員の削除を行う。例外はサーブレットで行う
        deleteEmpDAO.deleteEmp(deleteEmpList);

        return true;
    }
}
