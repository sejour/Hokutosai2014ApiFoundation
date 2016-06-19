package hokutosai.api.foundation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

// サーブレットで発生したエラーを処理します。
public class ServletError extends ErrorHandler {
	
	// HokutosaiApiExceptionによるエラーを処理します。
	public static void Handle(HokutosaiApiException e, HttpServlet servlet, HttpServletResponse response)
	{	
		// 例外レポート
		ExceptionReport.report(servlet.getServletContext().getServletContextName(), servlet.getServletName(), e);
		
		int errorCode = e.getErrorCode();
		
		// HTTPステータスコード設定
		response.setStatus(errorCode / 100);
		
		// errorオブジェクト送付
		SendError(errorCode, response);
	}
	
	// 例外によるエラーを処理します。
	public static void Handle(Throwable e, HttpServlet servlet) throws ServletException
	{
		// 例外レポート
		ExceptionReport.report(servlet.getServletContext().getServletContextName(), servlet.getServletName(), e);
		
		// 再度例外送出
		throw new ServletException(e);
	}
	
}
