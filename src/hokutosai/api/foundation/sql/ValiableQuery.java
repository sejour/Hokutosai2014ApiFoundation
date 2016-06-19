package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

// 変数を持つSQLクエリを生成します。
public class ValiableQuery extends SequentialParametersQuery {

	private String statement;
	
	public ValiableQuery(String statement) throws HokutosaiApiException
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
			throw new HokutosaiApiException(50000, "ValiableQueryはシーケンシャルクエリの先頭として使用することはできません。");
		}
		
		if (this.params.isEmpty()) {
			throw new HokutosaiApiException(40002, "ValiableQueryにパラメータが存在しません。");
		}
		
		// sqlに追加
		sql.append(" " + statement);
		
		// シーケンスのテンプレートを生成
		if( this.sequence != null ) {
			this.sequence.generateTemplate(sql);
		}
	}

}
