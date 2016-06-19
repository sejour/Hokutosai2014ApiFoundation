package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

// シーケンシャルクエリから成るSELECT句を表します。
public final class SelectQuery extends SequentialQuery {

	// ステートメント
	private String statement;
	
	// SelectQueryのインスタンスを生成します。
	public SelectQuery(String statement) throws HokutosaiApiException 
	{
		if (statement == null || statement.equals("")) {
			throw new HokutosaiApiException(50000, "ステートメントがnullまたは空です。");
		}
		
		this.statement = statement;
	}
	
	// SelectQueryのインスタンスを生成します。
	public SelectQuery(String selectQuery, String joinQuery) throws HokutosaiApiException 
	{
		if (selectQuery == null || selectQuery.equals("")) {
			throw new HokutosaiApiException(50000, "SELECT句がnullまたは空です。");
		}
		
		if (joinQuery == null || joinQuery.equals("")) {
			throw new HokutosaiApiException(50000, "JOIN句がnullまたは空です。");
		}
		
		this.statement = String.format("%s %s", selectQuery, joinQuery);
	}
	
	// ステートメントを取得します。
	public String getStatement()
	{
		return this.statement;
	}
	
	@Override
	protected void generateTemplate(StringBuilder sql) throws HokutosaiApiException
	{
		if (sql.length() != 0) {
			throw new HokutosaiApiException(50000, "SELECT句はシーケンスの先頭に配置する必要があります。");
		}
		
		// sqlに追加
		sql.append(this.statement);
		
		// シーケンスのテンプレートを生成
		if( this.sequence != null ) {
			this.sequence.generateTemplate(sql);
		}
	}

}
