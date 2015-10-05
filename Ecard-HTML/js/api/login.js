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
			url: '/ecard-api/login',
			data: JSON.stringify(postData),
			datatype: 'json',
			contentType: 'application/json',
			async: false,
			success: function(json){
				result.status = STATUS.SUCCESS;
				result.data = json;
			},
			error: function(json){
				result.status = STATUS.ERROR;
				result.data = json;
			}
		});

		return result;
	}

};