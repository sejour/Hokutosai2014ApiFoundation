package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

// DELETE句を生成するクラス
public class DeleteQuery extends SequentialQuery {

	String tableName;
	
	// DeleteQueryのインスタンスを生成します。
	public DeleteQuery(String tableName) throws HokutosaiApiException
	{
		if (tableName == null || tableName.equals("")) {
			throw new HokutosaiApiException(50000, "ステートメントがnullまたは空です。");
		}
		
		this.tableName = tableName;
	}
	
	@Override
	protected void generateTemplate(StringBuilder sql) throws HokutosaiApiException
	{
		if (sql.length() != 0) {
			throw new HokutosaiApiException(50000, "DELETE句はシーケンスの先頭に配置する必要があります。");
		}
		
		// sqlに追加
		sql.append(String.format("DELETE FROM %s", this.tableName));
		
		// シーケンスのテンプレートを生成
		if( this.sequence != null ) {
			this.sequence.generateTemplate(sql);
		}
	}

}
