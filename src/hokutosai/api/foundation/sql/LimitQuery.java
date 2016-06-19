package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

// LIMIT句を生成します。
public final class LimitQuery extends SequentialQuery {

	public LimitQuery(int count) throws HokutosaiApiException
	{
		if ( count <= 0 ) {
			throw new HokutosaiApiException(40002, "取得件数が0以下です。");
		}
		
		this.params.add(new SQLIntParameter("?", count));
	}
	
	public LimitQuery(int count, int offset) throws HokutosaiApiException
	{
		if ( count <= 0 ) {
			throw new HokutosaiApiException(40002, "取得件数が0以下です。");
		}
		
		this.params.add(new SQLTwoIntParameter("?, ?", offset, count));
	}
	
	@Override
	protected void generateTemplate(StringBuilder sql) throws HokutosaiApiException
	{
		if (sql.length() == 0) {
			throw new HokutosaiApiException(50000, "LIMIT句はシーケンシャルクエリの先頭として使用することはできません。");
		}
		
		// sqlに追加
		sql.append(String.format(" LIMIT %s", this.params.get(0).getExpression()));
		
		// シーケンスのテンプレートを生成
		if( this.sequence != null ) {
			this.sequence.generateTemplate(sql);
		}
	}
}
