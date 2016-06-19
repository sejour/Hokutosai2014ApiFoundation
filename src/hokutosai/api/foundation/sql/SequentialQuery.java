package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 動的に生成するシーケンシャルなSQLクエリを抽象化します。
public abstract class SequentialQuery {

	// クエリのシーケンス
	protected SequentialQuery sequence;
	
	// パラメータリスト
	protected List<SQLParameter> params;
	
	// SequentialQueryのインスタンスを生成します。
	public SequentialQuery()
	{
		this.params = new ArrayList<SQLParameter>();
		this.sequence = null;
	}
	
	// シーケンスを設定します。
	// 戻り値はこのオブジェクトです。
	// シーケンスの連結は
	//   select.setSequence(where.setSequence(orderby.setSequence(limit)));
	// のように行うことができます。
	public SequentialQuery setSequence(SequentialQuery sequence)
	{
		this.sequence = sequence;
		
		return this;
	}
	
	// PreparedStatementを生成します。
	public PreparedStatement generateStatement(Connection connection) throws HokutosaiApiException
	{
		try {
			StringBuilder sql = new StringBuilder();
			
			// テンプレート生成
			this.generateTemplate(sql);
			
			// テンプレートからステートメントを生成
			PreparedStatement statement = connection.prepareStatement(sql.toString());
			
			// プリペアドステートメントにパラメータバリューを設定する
			this.putValues(statement, 1);
			
			return statement;
		} catch (SQLException e) {
			throw new HokutosaiApiException(40002);
		}
	}
	
	// プリペアドステートメントのテンプレートSQLを生成します。
	// このメソッド定義の最終行で必ず
	//   if ( this.sequential != null ) { this.sequential.generateTemplate(sql) }
	// を記述してください。
	protected abstract void generateTemplate(StringBuilder sql) throws HokutosaiApiException;
	
	// プリペアドステートメントにパラメータバリューを設定する
	private void putValues(PreparedStatement statement, int index) throws HokutosaiApiException, SQLException
	{
		int nextIndex = index;
		
		for (SQLParameter param : this.params)
		{
			nextIndex = param.putValues(statement, nextIndex);
		}
		
		if (this.sequence != null) {
			this.sequence.putValues(statement, nextIndex);
		}
	}
	
}
