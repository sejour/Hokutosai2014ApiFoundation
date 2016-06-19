package hokutosai.api.foundation.sql;

import hokutosai.api.foundation.HokutosaiApiException;

// 「Hokutosai Database」で定義されている投票の状態の列挙パラメータ
public class VotingStatusParameter extends SQLEnumParameter {

	public VotingStatusParameter(String expression, String value) throws HokutosaiApiException 
	{
		super(expression, value);
	}

	@Override
	protected boolean valueIsValid(String value) 
	{
		if( value.equals("maintenance") || value.equals("accept") | value.equals("finished") ){
			return true;
		}
		
		return false;
	}
	
}
