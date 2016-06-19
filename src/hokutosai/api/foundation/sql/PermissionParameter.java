package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

// 「Hokutosai Database」で定義されているパーミッションの列挙パラメータ
public class PermissionParameter extends SQLEnumParameter {

	public PermissionParameter(String expression, String value) throws HokutosaiApiException 
	{
		super(expression, value);
	}

	@Override
	protected boolean valueIsValid(String value) 
	{
		if( value.equals("deny") || value.equals("allow") ){
			return true;
		}
		
		return false;
	}
	
}
