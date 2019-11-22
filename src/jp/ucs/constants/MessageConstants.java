/**
システム名：社員管理システム
クラス名  ：Message
処理概要  ：メッセージ一覧
プロジェクト名：HrsmUcs(共通)
作成者    ：小嶋貫太
作成日付  ：2019/06/24
 */
package jp.ucs.constants;

public class MessageConstants {

	// 1-10
	public static final String LOGIN_ERR = "社員IDもしくはパスワードが正しくありません";
	public static final String DELEMP_MSG01 = "削除する社員を選択してください";
	public static final String DELEMP_MSG02 = "該当する社員が見当たりません";
	public static final String DELEMP_ERR01 = "削除する社員が未選択です";
	public static final String DB_ERR01 = "エラーが発生しました。SEに問い合わせてください";
	public static final String DELEMP_MSG03 = "以下の社員を削除しますか?";
	public static final String DELEMP_MSG04 = "社員を削除しました";
	public static final String REGEMP_ERR01 = "氏名が入力されていません";
	public static final String REGEMP_ERR02 = "ふりがなが入力されていません";
	public static final String REGEMP_ERR03 = "部門が選択されていません";
	// 11-20
	public static final String REGEMP_ERR04 = "パスワードが入力されていません";
	public static final String REGEMP_ERR05 = "入社年月日が入力されていません";
	public static final String CHANGE_MSG01 = "社員情報を変更しますか?";
	public static final String CHANGE_MSG02 = "の社員情報を変更しました。";
	public static final String REGEMP_MSG01 = "以下の情報を登録しますか？";
	public static final String REGEMP_MSG02 = "一致する社員はいません";
	public static final String REGEMP_ERR06 = "氏名は全角30字以内で入力してください";
	public static final String REGEMP_ERR07 = "ふりがなは全角40字以内で入力してください";
	public static final String REGEMP_ERR08 = "パスワードは半角8字～16字で入力してください";
	public static final String REGEMP_ERR09 = "入社年月日は半角10字で入力してください";

	// 20-
	public static final String REGEMP_ERR10 = "ひらがなで入力してください";
	public static final String REGEMP_ERR11 = "有効な年月日ではありません";
	public static final String LICENS_ERR12 = "資格が選ばれていません";
}
