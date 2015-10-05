
/* ******************************
** 汎用JSファイル
** ****************************** */

/* ******************************
** 定数
** ****************************** */
var STATUS ={
	SUCCESS: 0,
	ERROR: 1
};


/**
 * エラーコンテンツ表示（データ取得時エラー）
 * @param エラーデータ
 */
function dataGetError(errData){

	// 未ログインエラー
	if(errData.status == ERROR_CODE.NOT_LOGIN){
		location.href = "/";
		exit;
	}
	return;
}
