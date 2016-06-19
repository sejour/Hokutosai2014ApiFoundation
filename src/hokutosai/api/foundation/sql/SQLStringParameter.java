package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// SQLクエリに使用される文字列型のパラメータ
public class SQLStringParameter extends SQLParameter {
	
	private String value;
	
	public SQLStringParameter(String expression, String value) throws HokutosaiApiException
	{
		super(expression);
		
		if( value == null || value.equals("") )
		{
			throw new HokutosaiApiException(40002);
		}
		
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}

	@Override
	public int putValues(PreparedStatement statement, int index) throws SQLException, HokutosaiApiException
	{
		statement.setString(index++, this.value);
		
		return index;
	}
	
}
