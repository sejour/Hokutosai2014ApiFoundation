package hokutosai.api.foundation.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

// JsonArray
public class JsonArray extends Json {

	public JsonArray(ObjectMapper mapper, ArrayNode node)
	{
		super(mapper, node);
	}
	
}
