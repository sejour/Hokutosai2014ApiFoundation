package hokutosai.api.foundation;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class FilterError extends ErrorHandler {

	// HokutosaiApiExceptionによるエラーを処理します。
	public static void Handle(HokutosaiApiException e, String contextName, String filterName, ServletResponse response)
	{
		// 例外レポート
		ExceptionReport.report(contextName, filterName, e);
		
		int errorCode = e.getErrorCode();
		
		// HTTPステータスコード設定
		((HttpServletResponse)response).setStatus(errorCode / 100);
		
		// errorオブジェクト送付
		SendError(errorCode, response);
	}
	
	// 例外によるエラーを処理します。
	public static void Handle(Throwable e, String contextName, String filterName) throws ServletException
	{
		// 例外レポート
		ExceptionReport.report(contextName, filterName, e);
		
		// 再度例外送出
		throw new ServletException(e);
	}
	
}
