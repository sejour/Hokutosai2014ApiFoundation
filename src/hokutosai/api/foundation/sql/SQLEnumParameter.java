package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// SQLクエリに使用される列挙型のパラメータ
public abstract class SQLEnumParameter extends SQLStringParameter {
	
	public SQLEnumParameter(String expression, String value) throws HokutosaiApiException
	{
		super(expression, value);
	}

	@Override
	public int putValues(PreparedStatement statement, int index) throws HokutosaiApiException, SQLException 
	{
		if( !this.valueIsValid(this.getValue()) ) {
			throw new HokutosaiApiException(40002);
		}
		
		return super.putValues(statement, index);
	}
	
	// 有効な列挙値であるか判別します。
	protected abstract boolean valueIsValid(String value);
	
}
