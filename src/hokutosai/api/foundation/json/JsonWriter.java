package hokutosai.api.foundation.json;

import hokutosai.api.foundation.HokutosaiApiException;

import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Jsonを出力ストリームに書き込むためのクラスメソッドを提供します。
public class JsonWriter {
	
	public static void WriteStream(OutputStream out, Json json) throws HokutosaiApiException, JsonGenerationException, JsonMappingException, IOException
	{
		ObjectMapper mapper = json.getObjectMapper();
		
		mapper.writeValue(out, json.getNode());
	}
	
}
