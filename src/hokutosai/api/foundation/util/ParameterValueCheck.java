package hokutosai.api.foundation.util;

import hokutosai.api.foundation.HokutosaiApiException;

// クエリパラメータの値を検査します。
public class ParameterValueCheck {

	// 必須パラメータバリューがnullでないかを確認してパラメータバリューを返します。
	// nullであった場合はHokutosaiApiException(40001)をスローします。
	public static String require(String value) throws HokutosaiApiException
	{
		if (value == null) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_EXIST_REQUIRE_PARAM);
		}
		
		return value;
	}
	
	// 整数値のパラメータバリューが指定された範囲内であるかどうかを確認してパラメータバリューを整数値として返します。
	// バリューが整数値でなかった場合や範囲外の整数値であった場合はHokutosaiApiException(40002)をスローします。
	public static int between(String value, int min, int max) throws HokutosaiApiException
	{
		try {
			
			int num = Integer.parseInt(value);
			 
			if (num < min || num > max) {
				throw new HokutosaiApiException(HokutosaiApiException.ER_BAD_VALUE);
			}
				
			return num;
			
		} catch (Throwable e) {
			throw new HokutosaiApiException(HokutosaiApiException.ER_BAD_VALUE, e);
		}
	}
	
}
