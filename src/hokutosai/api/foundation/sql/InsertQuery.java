package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

// INSERT句を生成するクラス
public class InsertQuery extends SequentialQuery {

	// 挿入先のテーブル
	private String intoTable;
	
	// 挿入先のテーブルを指定してInsertQueryのインスタンスを生成します。
	public InsertQuery(String intoTable) throws HokutosaiApiException
	{
		if (intoTable == null || intoTable.equals("")) {
			throw new HokutosaiApiException(50000, "ステートメントがnullまたは空です。");
		}
		
		this.intoTable = intoTable;
	}
	
	@Override
	protected void generateTemplate(StringBuilder sql) throws HokutosaiApiException
	{
		if (sql.length() != 0) {
			throw new HokutosaiApiException(50000, "INSERT句はシーケンスの先頭に配置する必要があります。");
		}
		
		if (this.params.isEmpty()) {
			throw new HokutosaiApiException(40003, "レコードに挿入するパラメータが存在しません。");
		}
		
		StringBuilder insertQuery = new StringBuilder(String.format("INSERT INTO %s (", this.intoTable));
		
		int end = this.params.size() - 1;
		
		// columns
		for (int i=0; i < end; ++i)
		{
			insertQuery.append(String.format("%s, ", this.params.get(i).getExpression()));
		}
		insertQuery.append(String.format("%s) VALUES (", this.params.get(end).getExpression()));
		
		// values
		for (int i=0; i < end; ++i)
		{
			insertQuery.append("?, ");
		}
		insertQuery.append("?)");
		
		// sqlに追加
		sql.append(insertQuery.toString());
		
		// シーケンスのテンプレートを生成
		if( this.sequence != null ) {
			this.sequence.generateTemplate(sql);
		}
	}
	
	public void insertStringColumn(String columnName, String value) throws HokutosaiApiException
	{
		this.params.add(new SQLStringParameter(columnName, value));
	}
	
	public void insertIntColumn(String columnName, String value) throws HokutosaiApiException
	{
		this.params.add(new SQLIntParameter(columnName, value));
	}
	
	public void insertDoubleColumn(String columnName, String value) throws HokutosaiApiException
	{
		this.params.add(new SQLDoubleParameter(columnName, value));
	}
	
	public void insertBooleanColumn(String columnName, String value) throws HokutosaiApiException
	{
		this.params.add(new SQLBooleanParameter(columnName, value));
	}
	
	public void insertUrlColumn(String columnName, String url) throws HokutosaiApiException
	{
		this.params.add(new SQLUrlParameter(columnName, url));
	}
	
	public void insertDateColumn(String columnName, String date) throws HokutosaiApiException
	{
		this.params.add(new SQLDateParameter(columnName, date));
	}
	
	public void insertTimeColumn(String columnName, String time) throws HokutosaiApiException
	{
		this.params.add(new SQLTimeParameter(columnName, time));
	}
	
	public void insertDatetimeColumn(String columnName, String dateTime) throws HokutosaiApiException
	{
		this.params.add(new SQLDatetimeParameter(columnName, dateTime));
	}
	
	public void insertTimestampColumn(String columnName, String timeStamp) throws HokutosaiApiException
	{
		this.params.add(new SQLTimestampParameter(columnName, timeStamp));
	}
	
	public void insertEnumColumn(SQLEnumParameter enumColumn) throws HokutosaiApiException
	{
		this.params.add(enumColumn);
	}
	
}

