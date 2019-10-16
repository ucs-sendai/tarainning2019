package jp.ucs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.constants.MessageConstants;
import jp.ucs.exception.HrsmUcsDBException;

public class DeleteEmpDAO extends DAOProperty  {

	/**
     * メソッド名：deleteEmp
     * 説明：削除選択された社員を社員表から削除するメソッド
     * @param  deleteEmpList :削除選択された社員のリスト
     * @return true   :削除が成功したときに返す
     */
    public boolean deleteEmp(List<EmployeeBean> deleteEmpList) throws HrsmUcsDBException{

        //削除選択された社員のリストがない場合、例外を発生させる
        if (deleteEmpList == null || deleteEmpList.size() == 0) {
            throw new HrsmUcsDBException(MessageConstants.DB_ERR01);
        }

        //交差エンティティとなるため先に資格取得表の削除を行う
        deleteHaveLic(deleteEmpList);

        //例外処理
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_ID, PWD)) {

        	StringBuilder sb = new StringBuilder();

            PreparedStatement pstmt = conn.prepareStatement(sb.toString());

            //イテレータの作成
            Iterator<EmployeeBean> it = deleteEmpList.iterator();

            //削除選択された社員リストの最後まで処理を行う
            while (it.hasNext()) {
                //Emploeeの社員IDを属性IDと連番IDに分ける
                String deleteId = it.next().getEmpId();
                String propertyId = deleteId.substring(0, 4);
                String serialId = deleteId.substring(4, 8);

                //SQL文にリストから取り出したIDをセット
                pstmt.setString(1, propertyId);
                pstmt.setString(2, serialId);

                //削除実行
                pstmt.executeUpdate();

            }

            //データベースエラー時例外を発生させメッセージを引数で渡す
            //処理はサーブレットで行う
        } catch (SQLException e) {
            throw new HrsmUcsDBException(MessageConstants.DB_ERR01);
        }

        return true;
    }

    /**
     * メソッド名：deleteHaveLic
     * 説明：削除選択された社員を資格取得表から削除するメソッド
     * @param  deleteEmpList :削除選択された社員のリスト
     * @return true   :データベースでエラーが未発生時返す
     */
    private boolean deleteHaveLic(List<EmployeeBean> deleteEmpList) throws HrsmUcsDBException{

        //例外処理
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_ID, PWD)) {

        	StringBuilder sb = new StringBuilder();


            PreparedStatement pstmt = conn.prepareStatement(sb.toString());

            //イテレータの作成
            Iterator<EmployeeBean> it = deleteEmpList.iterator();

            //削除選択された社員リストの最後まで処理を行う
            while (it.hasNext()) {
                //Emploeeの社員IDを属性IDと連番IDに分ける
                String deleteId = it.next().getEmpId();
                String propertyId = deleteId.substring(0, 4);
                String serialId = deleteId.substring(4, 8);

                //SQL文にリストから取り出したIDをセット
                pstmt.setString(1, propertyId);
                pstmt.setString(2, serialId);

                //削除実行
                pstmt.executeUpdate();
            }

            //データベースエラー時例外を発生させメッセージを引数で渡す
            //処理はサーブレットで行う
        } catch (SQLException e) {
            throw new HrsmUcsDBException(MessageConstants.DB_ERR01);
        }

        return true;
    }

}
