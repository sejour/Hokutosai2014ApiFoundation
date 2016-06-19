package hokutosai.api.foundation.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// ResultSetがJsonフォーマットにシリアライズされた結果
public class Json {
	
	private ObjectMapper mapper;
	
	private JsonNode node;
	
	public Json(ObjectMapper mapper, JsonNode node)
	{
		this.mapper = mapper;
		this.node = node;
	}
	
	public ObjectMapper getObjectMapper()
	{
		return this.mapper;
	}
	
	public JsonNode getNode()
	{
		return this.node;
	}
}
