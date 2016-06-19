package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// SQLクエリで使用される2個のバリューを持つ整数型のパラメータ
public class SQLTwoIntParameter extends SQLParameter {

	private int firstValue;
	private int secondValue;
	
	public SQLTwoIntParameter(String expression, String firstValue, String secondValue) throws HokutosaiApiException
	{
		super(expression);
		
		try {
			this.firstValue = Integer.parseInt(firstValue);
			this.secondValue = Integer.parseInt(secondValue);
		} catch (Throwable e) {
			throw new HokutosaiApiException(40002, e);
		}
	}
	
	public SQLTwoIntParameter(String expression, int firstValue, int secondValue)
	{
		super(expression);
		
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}
	
	public int getFirstValue()
	{
		return this.firstValue;
	}

	public int getSecondValue()
	{
		return this.secondValue;
	}
	
	@Override
	public int putValues(PreparedStatement statement, int index) throws HokutosaiApiException, SQLException
	{
		statement.setInt(index++, this.firstValue);
		
		statement.setInt(index++, this.secondValue);
		
		return index;
	}
	
}
