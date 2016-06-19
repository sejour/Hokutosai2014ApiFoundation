package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

// コンスタントなSQLクエリを生成します。
public final class ConstantQuery extends SequentialQuery {

	private String statement;
	
	public ConstantQuery(String statement) throws HokutosaiApiException
	{
		if (statement == null || statement.equals("")) {
			throw new HokutosaiApiException(50000, "ステートメントがnullまたは空です。");
		}
		
		this.statement = statement;
	}
	
	@Override
	protected void generateTemplate(StringBuilder sql) throws HokutosaiApiException 
	{
		if (sql.length() == 0) {
			throw new HokutosaiApiException(50000, "ConstantQueryはシーケンシャルクエリの先頭として使用することはできません。");
		}
		
		// sqlに追加
		sql.append(" " + statement);
		
		// シーケンスのテンプレートを生成
		if( this.sequence != null ) {
			this.sequence.generateTemplate(sql);
		}
	}

}
