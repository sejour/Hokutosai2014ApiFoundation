package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

// SQLクエリに使用されるタイムスタンプ型のパラメータ
public class SQLTimestampParameter extends SQLParameter {

	private Timestamp value;
	
	public SQLTimestampParameter(String expression, String timestamp) throws HokutosaiApiException
	{
		super(expression);
		
		try {
			this.value = Timestamp.valueOf(timestamp);
		} catch (Throwable e) {
			throw new HokutosaiApiException(40002, e);
		}
	}
	
	public Timestamp getValue()
	{
		return this.value;
	}

	@Override
	public int putValues(PreparedStatement statement, int index) throws SQLException 
	{
		statement.setTimestamp(index++, this.value);
		
		return index;
	}
	
}
