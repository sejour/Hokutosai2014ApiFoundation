package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// SQLクエリに使用される定数式のパラメータ
public class SQLConstantParameter extends SQLParameter {

	public SQLConstantParameter(String expression)
	{
		super(expression);
	}

	@Override
	public int putValues(PreparedStatement statement, int index) throws HokutosaiApiException, SQLException 
	{
		return index;
	}
	
}
