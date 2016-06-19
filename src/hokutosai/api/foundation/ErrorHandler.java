package hokutosai.api.foundation;

import java.io.OutputStream;

import javax.servlet.ServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

// エラー処理を行う基本機能を提供します。
public class ErrorHandler {
	
	// errorオブジェクトをクライアントに送信します。
	protected static void SendError(int errorCode, ServletResponse response)
	{
		try {
			response.setContentType("text/json; charset=utf-8");
			
			OutputStream out = response.getOutputStream();
			
			ObjectMapper mapper = new ObjectMapper();
			
			ObjectNode rootNode = mapper.createObjectNode();
			
			rootNode.put("error", errorCode);
			
			mapper.writeValue(out, rootNode);
			
		} catch (Throwable e) {}
	}
	
}
