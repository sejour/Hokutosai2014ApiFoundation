package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// SQLクエリで使用される複数個の整数型のバリューから成るパラメータ
public class SQLSeveralIntParameter extends SQLParameter {

	private List<Integer> values;
	
	public SQLSeveralIntParameter(String expression, List<Integer> values) throws HokutosaiApiException
	{
		super(expression);
		
		if (values == null || values.isEmpty()) {
			throw new HokutosaiApiException(40002, "バリューが存在しません。");
		}
		
		this.values = values;
	}
	
	@Override
	public int putValues(PreparedStatement statement, int index) throws SQLException 
	{
		for (int val : this.values) {
			statement.setInt(index++, val);
		}
		
		return index;
	}
	
	public static SQLSeveralIntParameter newInstance(String expression, List<String> values) throws HokutosaiApiException
	{
		List<Integer> parsedValues = new ArrayList<Integer>();
		
		try {
			for (String val : values) {
				parsedValues.add(Integer.parseInt(val));
			}
		} catch (Throwable e) {
			throw new HokutosaiApiException(40002, e);
		}
		
		return new SQLSeveralIntParameter(expression, parsedValues);
	}

}
