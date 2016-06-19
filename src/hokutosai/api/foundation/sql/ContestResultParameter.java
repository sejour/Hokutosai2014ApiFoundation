package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

// 「Hokutosai Database」で定義されているコンテストの結果の列挙パラメータ
public class ContestResultParameter extends SQLEnumParameter {

	public ContestResultParameter(String expression, String value) throws HokutosaiApiException 
	{
		super(expression, value);
	}

	@Override
	protected boolean valueIsValid(String value) 
	{
		if( value.equals("no") || value.equals("champion") ){
			return true;
		}
		
		return false;
	}
	
}
