package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

// 「Hokutosai Database」で定義されているyes/noの列挙パラメータ
public class YesNoParameter extends SQLEnumParameter {

	public YesNoParameter(String expression, String value) throws HokutosaiApiException 
	{
		super(expression, value);
	}

	@Override
	protected boolean valueIsValid(String value) 
	{
		if( value.equals("no") || value.equals("yes") ){
			return true;
		}
		
		return false;
	}
	
}
