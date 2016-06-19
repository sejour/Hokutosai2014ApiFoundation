package hokutosai.api.foundation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

// 日付の文字列の相互変換を行います。
public class DateConverter {

	// Date型をString型に変換します。
	public static String toString(java.util.Date date, String format)
	{
		return (new SimpleDateFormat(format)).format(date);
	}
	
	// String型をDate型に変換します。
	public static java.util.Date toDate(String source, String format) throws ParseException
	{
		return (new SimpleDateFormat(format)).parse(source);
	}
	
	// 現在のDateをString型に変換して取得します。
	public static String nowToString(String format)
	{
		return (new SimpleDateFormat(format)).format(new java.util.Date());
	}
	
}
