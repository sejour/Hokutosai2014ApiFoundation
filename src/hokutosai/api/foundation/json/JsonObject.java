package hokutosai.api.foundation.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

// JsonObject
public class JsonObject extends Json {

	public JsonObject(ObjectMapper mapper, ObjectNode node)
	{
		super(mapper, node);
	}
	
}
