package hokutosai.api.foundation.auth;

import hokutosai.api.foundation.HokutosaiApiException;
import hokutosai.api.foundation.jdbc.HokutosaiApiStatusesDatabase;
import hokutosai.api.foundation.sql.SelectQuery;
import hokutosai.api.foundation.sql.WhereQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

// APIユーザーの認証を行います。
public final class ApiUserAuthorization extends Authorization {

	private static final String CREDENTIALS_TYPE = "ApiCredentials";
	
	// ApiUserAuthorizationのインスタンスを生成します。
	public ApiUserAuthorization(HokutosaiApiStatusesDatabase apiStatusesDatabase, HttpServletRequest request) throws HokutosaiApiException
	{
		super(apiStatusesDatabase, request);
	}

	// 認証処理を行います。認証処理エラーが発生した際はHokutosaiApiExceptionをスローします。
	@Override
	public void authorize() throws HokutosaiApiException, SQLException
	{
		String[] apiCredentials = this.getAuthorizationHeader().split(" ");
		
		if ( apiCredentials.length != 2 || !apiCredentials[0].equals(CREDENTIALS_TYPE) || apiCredentials[1].equals("") ) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_EXIST_CREDENTIALS, "ApiCredentialsのフォーマットエラー");
		}
		
		String[] credentials = apiCredentials[1].split(",");
		
		if (credentials.length != 2) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_EXIST_CREDENTIALS, "ApiCredentialsのフォーマットエラー");
		}
		
		String[] userId = credentials[0].split("=");
		String[] keyToken = credentials[1].split("=");
		
		if ( userId.length != 2 || keyToken.length != 2 ||
			!userId[0].equals("user_id") || !keyToken[0].equals("key_token") ||
			keyToken[1] == null || keyToken[1].equals("") ) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_EXIST_CREDENTIALS, "ApiCredentialsのフォーマットエラー");
		}
		
		this.verify(inspectTokenWithWhiteList(userId[1]), keyToken[1]);
	}
	
	private static final String SELECT_API_USERS = "SELECT api_users.user_id AS user_id, api_users.key_token AS key_token, api_users.permission AS user_permission, api_roles_permissions.permission AS endpoint_permission FROM api_users";
	private static final String INNER_JOIN_API_USERS = "INNER JOIN api_roles_permissions ON api_users.role = api_roles_permissions.role";
	
	// 資格情報を検証します。
	// 検証エラーが発生した場合、HokutosaiApiException(40102)を返します。
	// アクセスが許可されていない場合、HokutosaiApiException(40303 or 40304)を返します。
	private void verify(String userId, String keyToken) throws HokutosaiApiException, SQLException
	{
		SelectQuery select = new SelectQuery(SELECT_API_USERS, INNER_JOIN_API_USERS);
		WhereQuery where = new WhereQuery();
		
		where.addStringParameter("api_users.user_id = ?", userId);
		where.addStringParameter("api_roles_permissions.endpoint = ?", String.format("%s%s", this.request.getContextPath(), this.request.getServletPath()));
		
		select.setSequence(where);
		
		ResultSet resultSet = this.db.executeQuery(select);
		
		if (!resultSet.first()) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_BAD_CREDENTIALS, String.format("資格情報レコードを取得できませんでした。(user_id=%s)", userId));
		}
		
		String resultUserId = resultSet.getString("user_id");
		String resultKeyToken = resultSet.getString("key_token");
		String resultUserPermission = resultSet.getString("user_permission");
		String resultEndpointPermission = resultSet.getString("endpoint_permission");
			
		if ( resultUserId == null || resultKeyToken == null ||
			 !resultUserId.equals(userId) || !resultKeyToken.equals(keyToken) ) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_BAD_CREDENTIALS, String.format("資格情報が不正です。(user_id=%s)", userId));
		}
		
		if (resultUserPermission == null || !resultUserPermission.equals("allow")) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_USER_NOT_ALLOW, String.format("許可のない認証ユーザー。(user_id=%s)", userId));
		}
		
		if (resultEndpointPermission == null || !resultEndpointPermission.equals("allow")) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_ENDPOINT_NOT_ALLOW, String.format("許可のない認証ユーザー。(user_id=%s)", userId));
		}
	}
	
}
