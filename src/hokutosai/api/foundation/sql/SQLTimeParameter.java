package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

// SQLクエリに使用される時間型のパラメータ
public class SQLTimeParameter extends SQLParameter {

	private Time value;
	
	public SQLTimeParameter(String expression, String time) throws HokutosaiApiException
	{
		super(expression);
		
		try {
			this.value = Time.valueOf(time);
		} catch (Throwable e) {
			throw new HokutosaiApiException(40002, e);
		}
	}
	
	public Time getValue()
	{
		return this.value;
	}

	@Override
	public int putValues(PreparedStatement statement, int index) throws SQLException 
	{
		statement.setTime(index++, this.value);
		
		return index;
	}
	
}
