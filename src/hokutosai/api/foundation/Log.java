package hokutosai.api.foundation;

import hokutosai.api.foundation.util.DateConverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

public class Log {
	
	// ログの出力先ルート
	private static final String OUT_ROOT = "/var/api/hokutosai-api/logs";
	
	// 日付の形式
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	// 情報レベル
	protected static final String LEVEL_INFO = "INFO";
	
	// 警告レベル
	protected static final String LEVEL_WARNING = "WARNING";
	
	// エラーレベル
	protected static final String LEVEL_ERROR = "ERROR";
	
	// 情報レベルのログを出力します。
	public static void info(String contextName, String servletOrFilterName, String source, String message)
	{
		logging(contextName, servletOrFilterName, LEVEL_INFO, source, message);
	}
	
	// 警告レベルのログを出力します。
	public static void warning(String contextName, String servletOrFilterName, String source, String message)
	{
		logging(contextName, servletOrFilterName, LEVEL_WARNING, source, message);
	}
	
	// エラーレベルのログを出力します。
	public static void error(String contextName, String servletOrFilterName, String source, String message)
	{
		logging(contextName, servletOrFilterName, LEVEL_ERROR, source, message);
	}
	
	// ロギングします。
	// outFilePath: 出力ファイルパス
	// level: ログレベル
	// location: ログのソース
	// message: ログの本文
	private static void logging(String contextName, String servletOrFilterName, String level, String source, String message)
	{
		PrintWriter writer = null;
		
		try {
		
			String outFilePath = String.format("%s/%s/%s-%s.log", OUT_ROOT, contextName, servletOrFilterName, DateConverter.nowToString("yyyyMMDD"));
			
			writer = new PrintWriter(new BufferedWriter(new FileWriter(outFilePath, true)));
			
			writer.println(String.format("[%s %s@%s] %s", DateConverter.toString(new Date(), DATE_FORMAT), level, source, message));
	
		} catch (Throwable e) {}
		finally {
			if( writer != null ){
				writer.close();
			}
		}
	}
	
}
