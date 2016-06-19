package hokutosai.api.foundation.auth;

import hokutosai.api.foundation.HokutosaiApiException;
import hokutosai.api.foundation.jdbc.HokutosaiApiStatusesDatabase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

// 「Hokutosai API」で定義されるAPIユーザーの認証処理を提供します。
public abstract class Authorization {
	
	// 認証情報データベース
	protected HokutosaiApiStatusesDatabase db;
	
	// APIリクエスト
	protected HttpServletRequest request;
	
	// 認証資格情報が付加されたヘッダの名前
	protected static final String CREDENTIALS_HEADER_NAME = "Authorization";
	
	// Authorizationヘッダの値
	private String authorizationHeader;

	// Authorizationのインスタンスを生成します。
	// apiStatusesDatabase: 接続済みのデータベース
	public Authorization(HokutosaiApiStatusesDatabase apiStatusesDatabase, HttpServletRequest request) throws HokutosaiApiException
	{
		this.db = apiStatusesDatabase;
		this.request = request;
		
		// Authorizationヘッダ値取得
		this.authorizationHeader = this.request.getHeader(CREDENTIALS_HEADER_NAME);
		
		if (this.authorizationHeader == null || this.authorizationHeader.equals("")) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_EXIST_CREDENTIALS);
		}
	}
	
	// 認証処理を行います。認証処理エラーが発生した際はHokutosaiApiExceptionをスローします。
	public abstract void authorize() throws HokutosaiApiException, SQLException;
	
	// Authorizationヘッダの値を取得します。
	protected String getAuthorizationHeader()
	{
		return this.authorizationHeader;
	}
	
	// 資格情報のトークンをブラックリスト形式で検査します。
	// 検査スピードは速いですが欠陥が含まれる可能性があります。
	// 検査の結果、安全なトークンと認められた場合はそのトークンを返します。
	// 検査の結果、危険なトークンと判断された場合はHokutosaiApiException(40301)をスローします。
	// tokenがnullまたは空文字列であった場合はHokutosaiApiException(40401)をスローします。
	protected static String inspectTokenWithBlackList(String token) throws HokutosaiApiException
	{
		if (token == null || token .equals("")) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_EXIST_CREDENTIALS);
		}
		
		int length = token.length();
		
		if (length == 0) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_REJECT_AUTH);
		}
		
		char c = ' ';
		
		for (int i=0 ; i < length ; ++i) {
			c = token.charAt(i);
			switch (c) {
			case ' ':
			case '\\':
			case '\'':
			case '\"':
			case '\b':
			case '\t':
			case '\n':
			case '\r':
			case '\f':
				throw new HokutosaiApiException(HokutosaiApiException.ER_REJECT_AUTH);
			}
		}
		
		return token;
	}
	
	// 資格情報のトークンをホワイトリスト形式で検査します。
	// 検査スピードは劣りますがセキュリティ信頼度は高まります。
	// 検査の結果、安全なトークンと認められた場合はそのトークンを返します。
	// 検査の結果、危険なトークンと判断された場合はHokutosaiApiException(40301)をスローします。
	// tokenがnullまたは空文字列であった場合はHokutosaiApiException(40401)をスローします。
	protected static String inspectTokenWithWhiteList(String token) throws HokutosaiApiException
	{
		if (token == null || token.equals("")) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_EXIST_CREDENTIALS);
		}
		
		int length = token.length();
		
		if (length == 0) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_REJECT_AUTH);
		}
		
		int i, j;
		char c = ' ';
		int whiteListLength = TOKEN_WHITE_LIST.length();
		
		for (i=0 ; i < length ; ++i) {
			c = token.charAt(i);
			for (j=0 ; j < whiteListLength ; ++j) {
				if (c == TOKEN_WHITE_LIST.charAt(j)) {
					break;
				}
			}
			if (j >= whiteListLength) {
				throw new HokutosaiApiException(HokutosaiApiException.ER_REJECT_AUTH);
			}
		}
		
		return token;
	}
	
	protected static final String TOKEN_WHITE_LIST = "abcdefghijklmnopqrstuvxxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
}
