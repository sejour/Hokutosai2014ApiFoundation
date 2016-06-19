package hokutosai.api.foundation;

// 「Hokutosai API」で定義されるエラーの内容を示す例外オブジェクト
public class HokutosaiApiException extends Exception {
	// 不正なリクエスト。 (エラーコード:40000) (HTTPステータスコード:400)
	public static final int ER_BAD_REQUEST = 40000;
	
	// 必須パラメータが存在しない。 (エラーコード:40001) (HTTPステータスコード:400)
	public static final int ER_NOT_EXIST_REQUIRE_PARAM = 40001;
	
	// パラメータの値が不正である。 (エラーコード:40002) (HTTPステータスコード:400)
	public static final int ER_BAD_VALUE = 40002;
	
	// POSTメソッドにおけるリソースの投稿において、内容を示すパラメータが一つも存在しない。 (エラーコード:40003) (HTTPステータスコード:400)
	public static final int ER_NOT_EXIST_POST_PARAM = 40003;
	
	// 認証エラー。 (エラーコード:40100) (HTTPステータスコード:401)
	public static final int ER_AUTH_FAILURE = 40100;
	
	// リクエストにCredentialsが付加されていない。 (エラーコード:40101) (HTTPステータスコード:401)
	public static final int ER_NOT_EXIST_CREDENTIALS = 40101;
	
	// リクエストに付加されたCredentialsが不正である。 (エラーコード:40102) (HTTPステータスコード:401)
	public static final int ER_BAD_CREDENTIALS = 40102;
		
	// リクエストの拒否。 (エラーコード:40300) (HTTPステータスコード:403)
	public static final int ER_REJECT_REQUEST = 40300;
	
	// 認証リクエストの拒否。 (エラーコード:40301) (HTTPステータスコード:403)
	public static final int ER_REJECT_AUTH = 40301;
	
	// 一時的にアクセスが許可されていない。 (エラーコード:40302) (HTTPステータスコード:403)
	public static final int ER_TEMPORARILY_NOT_ALLOW = 40302;
	
	// 認証ユーザーのパーミッションが許可されていない。 (エラーコード:40303) (HTTPステータスコード:403)
	public static final int ER_USER_NOT_ALLOW = 40303;
	
	// 認証ユーザーのロールではアクセスが許可されていないエンドポイントにアクセスされた。 (エラーコード:40303) (HTTPステータスコード:403)
	public static final int ER_ENDPOINT_NOT_ALLOW = 40304;
	
	// 見つからない。 (エラーコード:40400) (HTTPステータスコード:404)
	public static final int ER_NOT_FOUND = 40400;
	
	// レコードを1件も取得できなかった。 (エラーコード:40401) (HTTPステータスコード:404)
	public static final int ER_NOT_FOUND_RECORD = 40401;
	
	// 内部サーバーエラー。 (エラーコード:50000) (HTTPステータスコード:500)
	public static final int ER_INTERNAL_SERVER_ERROR = 50000;
	
	// メンテナンス中。 (エラーコード:50001) (HTTPステータスコード:500)
	public static final int ER_MAINTENANCE = 50001;
	
	// エラーコード
	protected int errorCode;
	
	// HokutosaiApiExceptionのインスタンスを生成します。
	public HokutosaiApiException(int errorCode)
	{
		super(String.format("[ERROR:%d]", errorCode));
		this.errorCode = errorCode;
	}
	
	// HokutosaiApiExceptionのインスタンスを生成します。
	public HokutosaiApiException(int errorCode, Throwable e)
	{
		super(String.format("[ERROR:%d]", errorCode), e);
		this.errorCode = errorCode;
	}
	
	// HokutosaiApiExceptionのインスタンスを生成します。
	public HokutosaiApiException(int errorCode, String message)
	{
		super(String.format("[ERROR:%d] %s", errorCode, message));
		this.errorCode = errorCode;
	}
	
	// HokutosaiApiExceptionのインスタンスを生成します。
	public HokutosaiApiException(int errorCode, String message, Throwable e)
	{
		super(String.format("[ERROR:%d] %s", errorCode, message), e);
		this.errorCode = errorCode;
	}	
	
	// エラーコードを取得します。
	public int getErrorCode()
	{
		return this.errorCode;
	}
}

