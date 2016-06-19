package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

// UPDATE句を生成するクラス
public final class UpdateQuery extends SequentialParametersQuery {

	// 更新を適用するテーブル
	private String updateTable;
	
	// UpdateQueryのインスタンスを生成します。
	public UpdateQuery(String updateTable) throws HokutosaiApiException
	{
		if (updateTable == null || updateTable.equals("")) {
			throw new HokutosaiApiException(50000, "ステートメントがnullまたは空です。");
		}
		
		this.updateTable = updateTable;
	}
	
	@Override
	protected void generateTemplate(StringBuilder sql) throws HokutosaiApiException
	{
		if (sql.length() != 0) {
			throw new HokutosaiApiException(50000, "UPDATE句はシーケンスの先頭に配置する必要があります。");
		}
		
		if (this.params.isEmpty()) {
			throw new HokutosaiApiException(40003, "アップデートの対象となるパラメータが存在しません。");
		}
		
		StringBuilder updateQuery = new StringBuilder(String.format("UPDATE %s SET %s", this.updateTable, this.params.get(0).getExpression()));
		
		int size = this.params.size();
		for (int i=1; i < size ; ++i)
		{
			updateQuery.append(String.format(", %s", this.params.get(i).getExpression()));
		}
		
		// sqlに追加
		sql.append(updateQuery.toString());
		
		// シーケンスのテンプレートを生成
		if( this.sequence != null ) {
			this.sequence.generateTemplate(sql);
		}
	}
	
	// インクリメント式を追加します。
	// 例: id = id + 1
	//     addIncrement("id");
	public void addIncrement(String columnName)
	{
		this.addConstantParameter(String.format("%s = %s + 1", columnName, columnName));
	}
	
	// デクリメント式を追加します。
	// 例: id = id - 1
	//     addDecrement("id");
	public void addDecrement(String columnName)
	{
		this.addConstantParameter(String.format("%s = %s - 1", columnName, columnName));
	}

}
