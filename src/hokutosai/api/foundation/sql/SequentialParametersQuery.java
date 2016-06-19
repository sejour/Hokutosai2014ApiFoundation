package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

// 動的パラメータを有するシーケンシャルなSQLクエリを表します。
public abstract class SequentialParametersQuery extends SequentialQuery {

	public SequentialParametersQuery() {}
	
	public void addParameter(SQLParameter param)
	{
		this.params.add(param);
	}
	
	public void addConstantParameter(String expression)
	{
		this.params.add(new SQLConstantParameter(expression));
	}
	
	public void addStringParameter(String expression, String value) throws HokutosaiApiException
	{
		this.params.add(new SQLStringParameter(expression, value));
	}
	
	public void addIntParameter(String expression, String value) throws HokutosaiApiException
	{
		this.params.add(new SQLIntParameter(expression, value));
	}
	
	public void addDoubleParameter(String expression, String value) throws HokutosaiApiException
	{
		this.params.add(new SQLDoubleParameter(expression, value));
	}
	
	public void addBooleanParameter(String expression, String value) throws HokutosaiApiException
	{
		this.params.add(new SQLBooleanParameter(expression, value));
	}
	
	public void addUrlParameter(String expression, String url) throws HokutosaiApiException
	{
		this.params.add(new SQLUrlParameter(expression, url));
	}
	
	public void addDateParameter(String expression, String date) throws HokutosaiApiException
	{
		this.params.add(new SQLDateParameter(expression, date));
	}
	
	public void addTimeParameter(String expression, String time) throws HokutosaiApiException
	{
		this.params.add(new SQLTimeParameter(expression, time));
	}
	
	public void addDatetimeParameter(String expression, String dateTime) throws HokutosaiApiException
	{
		this.params.add(new SQLDatetimeParameter(expression, dateTime));
	}
	
	public void addTimestampParameter(String expression, String timeStamp) throws HokutosaiApiException
	{
		this.params.add(new SQLTimestampParameter(expression, timeStamp));
	}
	
	public void addEnumParameter(SQLEnumParameter enumeration) throws HokutosaiApiException
	{
		this.params.add(enumeration);
	}
	
}
