package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

// 「Hokutosai Database」で定義されている公開状態の列挙パラメータ
public class ShowStatusParameter extends SQLEnumParameter {

	public ShowStatusParameter(String expression, String value) throws HokutosaiApiException 
	{
		super(expression, value);
	}

	@Override
	protected boolean valueIsValid(String value) 
	{
		if( value.equals("secret") || value.equals("publish") ){
			return true;
		}
		
		return false;
	}
	
}
