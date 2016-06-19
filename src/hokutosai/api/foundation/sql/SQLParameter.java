package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// SQLクエリに使用される動的パラメータを抽象化します。
public abstract class SQLParameter {

	// テンプレート式
	private String expression;
	
	// SQLParameterのインスタンスを生成します。
	public SQLParameter(String expression)
	{
		this.expression = expression;
	}
	
	// 式を取得します。
	public String getExpression()
	{
		return this.expression;
	}
	
	// パラメータバリューをプリペアドステートメントに設定します。
	// index: PreparedStatementの現在のインデックス
	// 戻り値: PreparedStatementの次のインデックス
	public abstract int putValues(PreparedStatement statement, int index) throws HokutosaiApiException, SQLException;
	
}
