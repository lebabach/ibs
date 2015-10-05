/* ******************************
** ログインクラスJSファイル
** ****************************** */

/**
 * コンストラクタ
 */
function Login() {}

/**
 * プロパティ
 */
Login.prototype = {

	/**
	 * ログイン処理
	 * @param ログインデータ
	 * @return スタッフ一覧
	 */
	login : function(postData){
		var result = {"status": "","data": ""};
		$.ajax({
			type: 'POST',
			url: 'checkLogin',
			data: JSON.stringify(postData),
			/*datatype: 'json',*/
			/*contentType: 'application/json',*/
			/*async: false,*/
			success: function(response){
				console.log(response);
				result.status = STATUS.SUCCESS;
				result.data = response;
			},
			error: function(response){
				console.log("DDDDDDDDDDDD" +response);
				result.status = STATUS.ERROR;
				result.data = response;
			}
		});

		return result;
	}

};