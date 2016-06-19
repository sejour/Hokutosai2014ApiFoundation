package hokutosai.api.foundation;

import hokutosai.api.foundation.util.DateConverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

// 例外のレポートを行うクラス
public class ExceptionReport {

	// 出力先
	private final static String OUT_ROOT = "/var/api/hokutosai-api/logs";
	
	// 日付の形式
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	// 例外レポートを出力します。
	public static void report(String contextName, String servletOrFilterName, Throwable e)
	{
		writeReport(contextName, servletOrFilterName, e, -1);
	}
	
	// 例外レポートを出力します。
	public static void report(String contextName, String servletOrFilterName, Throwable e, int errorCode)
	{
		writeReport(contextName, servletOrFilterName, e, errorCode);
	}
	
	// 例外レポートを出力します。
	public static void report(String contextName, String servletOrFilterName, HokutosaiApiException e)
	{
		writeReport(contextName, servletOrFilterName, e, e.getErrorCode());
	}
	
	// レポートを書き出します。
	private static void writeReport(String contextName, String servletOrFilterName, Throwable e, int errorCode)
	{
		PrintWriter writer = null;
		
		try {
			
			String outFilePath = String.format("%s/%s/%s-Exception-%s.log", OUT_ROOT, contextName, servletOrFilterName, DateConverter.nowToString("yyyyMMdd"));
			
			writer = new PrintWriter(new BufferedWriter(new FileWriter(outFilePath, true)));
			
			writer.println("################################# Exception Report Begin ###################################");
			
			writer.println(String.format("Datetime: %s", DateConverter.toString(new Date(), DATE_FORMAT)));
			writer.println(String.format("Exception: %s", e.toString()));
			writer.println(String.format("ErrorCode: %s", ( errorCode < 0 ? "Unknown" : errorCode ) ));
			writer.println(String.format("Message: %s", e.getMessage()));
			
			writer.println("\n----------------------- Stact Trace Begin -----------------------");
			e.printStackTrace(writer);
			writer.println("------------------------ Stact Trace End ------------------------");
			
			writer.println("################################# Exception Report End #####################################\n");
			
		} catch (Throwable _) { }
		finally {
			if( writer != null ){
				writer.close();
			}
		}
	}
}
