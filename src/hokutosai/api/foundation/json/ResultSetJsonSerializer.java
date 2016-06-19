package hokutosai.api.foundation.json;

import hokutosai.api.foundation.HokutosaiApiException;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 「Hokutosai Database」のクエリの実行から得られたResultSetを「Hokutosai API」で定義されているJsonオブジェクトにパースします。
public class ResultSetJsonSerializer {

	private ObjectMapper mapper;
	
	// ResultSetJsonSerializerのインスタンスを生成します。
	public ResultSetJsonSerializer()
	{
		this.mapper = new ObjectMapper();
	}
	
	// events (Array)
	public JsonArray toEventsArray(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		ArrayNode rootNode = this.mapper.createArrayNode();
		ObjectNode element = null;
		
		while(resultSet.next())
		{
			element = rootNode.addObject();
			
			element.put("event_id", resultSet.getInt("event_id"));
			element.put("title", resultSet.getString("title"));
			element.put("date", resultSet.getString("date"));
			element.put("start_time", resultSet.getString("start_time"));
			element.put("end_time", resultSet.getString("end_time"));
			this.setPlace(element.putObject("place"), resultSet);
			element.put("participants", resultSet.getString("participants"));
			element.put("detail", resultSet.getString("detail"));
			element.put("image_resource", resultSet.getString("image_resource"));
		}
		
		return new JsonArray(this.mapper, rootNode);
	}
	
	// event
	public JsonObject toEvent(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		if( !resultSet.first() ){
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_FOUND_RECORD);
		}
		
		ObjectNode rootNode = this.mapper.createObjectNode();
		
		rootNode.put("event_id", resultSet.getInt("event_id"));
		rootNode.put("title", resultSet.getString("title"));
		rootNode.put("date", resultSet.getString("date"));
		rootNode.put("start_time", resultSet.getString("start_time"));
		rootNode.put("end_time", resultSet.getString("end_time"));
		this.setPlace(rootNode.putObject("place"), resultSet);
		rootNode.put("participants", resultSet.getString("participants"));
		rootNode.put("detail", resultSet.getString("detail"));
		rootNode.put("image_resource", resultSet.getString("image_resource"));
		
		return new JsonObject(this.mapper, rootNode);
	}
	
	// event_tagを設定します。
	private void setEventTag(ObjectNode eventTagNode, ResultSet resultSetEventTag) throws SQLException
	{
		eventTagNode.put("event_id", resultSetEventTag.getInt("event_id"));
		eventTagNode.put("title", resultSetEventTag.getString("event_title"));
	}
	
	// shops (Array)
	public JsonArray toShopsArray(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		ArrayNode rootNode = this.mapper.createArrayNode();
		ObjectNode element = null;
		
		while(resultSet.next())
		{
			element = rootNode.addObject();
			
			element.put("shop_id", resultSet.getInt("shop_id"));
			element.put("name", resultSet.getString("name"));
			element.put("tenant", resultSet.getString("tenant"));
			element.put("sales", resultSet.getString("sales"));
			element.put("image_resource", resultSet.getString("image_resource"));
		}
		
		return new JsonArray(this.mapper, rootNode);
	}
	
	// shop
	public JsonObject toShop(ResultSet resultSetShop, ResultSet resultSetMenus) throws HokutosaiApiException, SQLException
	{
		if( !resultSetShop.first() ){
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_FOUND_RECORD);
		}
		
		ObjectNode rootNode = this.mapper.createObjectNode();
		
		rootNode.put("shop_id", resultSetShop.getInt("shop_id"));
		rootNode.put("name", resultSetShop.getString("name"));
		rootNode.put("tenant", resultSetShop.getString("tenant"));
		rootNode.put("sales", resultSetShop.getString("sales"));
		rootNode.put("place_id", resultSetShop.getInt("place_id"));
		rootNode.put("introduction", resultSetShop.getString("introduction"));
		rootNode.put("image_resource", resultSetShop.getString("image_resource"));
		rootNode.put("menus_extension", resultSetShop.getString("menus_extension"));
		
		ObjectNode assess = rootNode.putObject("assess");
		setShopAssess(assess, resultSetShop);
		
		ArrayNode menus = rootNode.putArray("menus");
		if (!setMenus(menus, resultSetMenus)) {
			rootNode.putNull("menus");
		}
		
		return new JsonObject(this.mapper, rootNode);
	}
	
	// shop_assess
	public JsonObject toShopAssess(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		if( !resultSet.first() ){
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_FOUND_RECORD);
		}
		
		ObjectNode rootNode = this.mapper.createObjectNode();
		
		rootNode.put("shop_id", resultSet.getInt("shop_id"));
		rootNode.put("total_score", resultSet.getInt("total_score"));
		rootNode.put("assessed_count", resultSet.getInt("assessed_count"));
		
		return new JsonObject(this.mapper, rootNode);
	}
	
	// shop_assessを設定します。
	private void setShopAssess(ObjectNode shopAssessNode, ResultSet resultSetShopAssess) throws SQLException
	{
		shopAssessNode.put("total_score", resultSetShopAssess.getInt("total_score"));
		shopAssessNode.put("assessed_count", resultSetShopAssess.getInt("assessed_count"));
	}
	
	// menusを設定します。
	private boolean setMenus(ArrayNode menusNode, ResultSet resultSetMenus) throws HokutosaiApiException, SQLException
	{
		ObjectNode element = null;
		
		while(resultSetMenus.next())
		{
			element = menusNode.addObject();
			
			element.put("item_id", resultSetMenus.getInt("item_id"));
			element.put("name", resultSetMenus.getString("name"));
			element.put("price", resultSetMenus.getInt("price"));
		}
		
		return element != null;
	}
	
	// shop_tagを設定します。
	private void setShopTag(ObjectNode shopTagNode, ResultSet resultSetShopTag) throws SQLException
	{
		shopTagNode.put("shop_id", resultSetShopTag.getInt("shop_id"));
		shopTagNode.put("name", resultSetShopTag.getString("shop_name"));
	}
	
	// exhibitions (Array)
	public JsonArray toExhibitionsArray(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		ArrayNode rootNode = this.mapper.createArrayNode();
		ObjectNode element = null;
		
		while(resultSet.next())
		{
			element = rootNode.addObject();
			
			element.put("exhibition_id", resultSet.getInt("exhibition_id"));
			element.put("title", resultSet.getString("title"));
			element.put("exhibitors", resultSet.getString("exhibitors"));
			element.put("displays", resultSet.getString("displays"));
			this.setPlace(element.putObject("place"), resultSet);
			element.put("detail", resultSet.getString("detail"));
			element.put("image_resource", resultSet.getString("image_resource"));
		}
		
		return new JsonArray(this.mapper, rootNode);
	}
	
	// exhibition
	public JsonObject toExhibition(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		if( !resultSet.first() ){
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_FOUND_RECORD);
		}
		
		ObjectNode rootNode = this.mapper.createObjectNode();
		
		rootNode.put("exhibition_id", resultSet.getInt("exhibition_id"));
		rootNode.put("title", resultSet.getString("title"));
		rootNode.put("exhibitors", resultSet.getString("exhibitors"));
		rootNode.put("displays", resultSet.getString("displays"));
		this.setPlace(rootNode.putObject("place"), resultSet);
		rootNode.put("detail", resultSet.getString("detail"));
		rootNode.put("image_resource", resultSet.getString("image_resource"));
		
		return new JsonObject(this.mapper, rootNode);
	}
	
	// exhibition_tagを設定します。
	private void setExhibitionTag(ObjectNode exhibitionTagNode, ResultSet resultSetExhibitionTag) throws SQLException
	{
		exhibitionTagNode.put("exhibition_id", resultSetExhibitionTag.getInt("exhibition_id"));
		exhibitionTagNode.put("title", resultSetExhibitionTag.getString("exhibition_title"));
	}
	
	// contest_statuses
	public JsonObject toContestStatuses(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		if( !resultSet.first() ){
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_FOUND_RECORD);
		}
		
		ObjectNode rootNode = this.mapper.createObjectNode();
		
		rootNode.put("entrys_status", resultSet.getString("entrys_status"));
		rootNode.put("voting_status", resultSet.getString("voting_status"));
		rootNode.put("results_status", resultSet.getString("results_status"));
		
		return new JsonObject(this.mapper, rootNode);
	}
	
	// contests (Array)
	public JsonArray toContestsArray(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		ArrayNode rootNode = this.mapper.createArrayNode();
		ObjectNode element = null;
		
		while(resultSet.next())
		{
			element = rootNode.addObject();
			
			element.put("section_id", resultSet.getString("section_id"));
			element.put("section_name", resultSet.getString("section_name"));
			element.put("detail", resultSet.getString("detail"));
		}
		
		return new JsonArray(this.mapper, rootNode);
	}
	
	// contest
	public JsonObject toContest(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		if( !resultSet.first() ){
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_FOUND_RECORD);
		}
		
		ObjectNode rootNode = this.mapper.createObjectNode();
		
		rootNode.put("section_id", resultSet.getString("section_id"));
		rootNode.put("section_name", resultSet.getString("section_name"));
		rootNode.put("detail", resultSet.getString("detail"));
		
		return new JsonObject(this.mapper, rootNode);
	}
	
	// entrys (Array)
	public JsonArray toEntrysArray(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		ArrayNode rootNode = this.mapper.createArrayNode();
		ObjectNode element = null;
		
		while(resultSet.next())
		{
			element = rootNode.addObject();
			
			element.put("section_id", resultSet.getString("section_id"));
			element.put("section_name", resultSet.getString("section_name"));
			element.put("entry_number", resultSet.getInt("entry_number"));
			element.put("entry_name", resultSet.getString("entry_name"));
			element.put("belong", resultSet.getString("belong"));
			element.put("profile", resultSet.getString("profile"));
			element.put("image_resource", resultSet.getString("image_resource"));
			element.put("result", resultSet.getString("result"));
		}
		
		return new JsonArray(this.mapper, rootNode);
	}
	
	// entry
	public JsonObject toEntry(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		if( !resultSet.first() ){
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_FOUND_RECORD);
		}
		
		ObjectNode rootNode = this.mapper.createObjectNode();
		
		rootNode.put("section_id", resultSet.getString("section_id"));
		rootNode.put("section_name", resultSet.getString("section_name"));
		rootNode.put("entry_number", resultSet.getInt("entry_number"));
		rootNode.put("entry_name", resultSet.getString("entry_name"));
		rootNode.put("belong", resultSet.getString("belong"));
		rootNode.put("profile", resultSet.getString("profile"));
		rootNode.put("image_resource", resultSet.getString("image_resource"));
		rootNode.put("result", resultSet.getString("result"));
		
		return new JsonObject(this.mapper, rootNode);
	}
	
	// contests_results (Array)
	public JsonArray toContestsResultsArray(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		ArrayNode rootNode = this.mapper.createArrayNode();
		ObjectNode element = null;
		
		while(resultSet.next())
		{
			element = rootNode.addObject();
			
			element.put("section_id", resultSet.getString("section_id"));
			element.put("entry_number", resultSet.getInt("entry_number"));
			element.put("name", resultSet.getString("name"));
			element.put("app_votes_count", resultSet.getInt("app_votes_count"));
			element.put("paper_votes_count", resultSet.getInt("paper_votes_count"));
			element.put("total", resultSet.getInt("total"));
			element.put("result", resultSet.getString("result"));
		}
		
		return new JsonArray(this.mapper, rootNode);
	}
	
	// contests_result
	public JsonObject toContestsResult(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		if( !resultSet.first() ){
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_FOUND_RECORD);
		}
		
		ObjectNode rootNode = this.mapper.createObjectNode();
		
		rootNode.put("section_id", resultSet.getString("section_id"));
		rootNode.put("entry_number", resultSet.getInt("entry_number"));
		rootNode.put("name", resultSet.getString("name"));
		rootNode.put("app_votes_count", resultSet.getInt("app_votes_count"));
		rootNode.put("paper_votes_count", resultSet.getInt("paper_votes_count"));
		rootNode.put("total", resultSet.getInt("total"));
		rootNode.put("result", resultSet.getString("result"));
		
		return new JsonObject(this.mapper, rootNode);
	}
	
	// news (Array)
	public JsonArray toNewsArray(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		ArrayNode rootNode = this.mapper.createArrayNode();
		ObjectNode element = null;
		ObjectNode relation = null;
		
		while(resultSet.next())
		{
			element = rootNode.addObject();
			
			element.put("news_id", resultSet.getInt("news_id"));
			element.put("title", resultSet.getString("title"));
			element.put("send_datetime", resultSet.getString("send_datetime"));
			element.put("sender_department", resultSet.getString("sender_department"));
			
			// related_event
			if ( resultSet.getString("event_id")  == null ){
				element.putNull("related_event");
			}
			else {
				relation = element.putObject("related_event");
				setEventTag(relation, resultSet);
			}
			
			// related_shop
			if ( resultSet.getString("shop_id")  == null ){
				element.putNull("related_shop");
			}
			else {
				relation = element.putObject("related_shop");
				setShopTag(relation, resultSet);
			}
			
			// related_exhibition
			if ( resultSet.getString("exhibition_id")  == null ){
				element.putNull("related_exhibition");
			}
			else {
				relation = element.putObject("related_exhibition");
				setExhibitionTag(relation, resultSet);
			}
			
			element.put("is_important", resultSet.getString("is_important"));
			element.put("text", resultSet.getString("text"));
			element.put("image_resource", resultSet.getString("image_resource"));
		}
		
		return new JsonArray(this.mapper, rootNode);
	}
	
	// news_detail
	public JsonObject toNewsDetail(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		if( !resultSet.first() ){
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_FOUND_RECORD);
		}
		
		ObjectNode rootNode = this.mapper.createObjectNode();
		ObjectNode relation = null;
		
		rootNode.put("news_id", resultSet.getInt("news_id"));
		rootNode.put("title", resultSet.getString("title"));
		rootNode.put("send_datetime", resultSet.getString("send_datetime"));
		rootNode.put("sender_department", resultSet.getString("sender_department"));
		
		// related_event
		if ( resultSet.getString("event_id")  == null ){
			rootNode.putNull("related_event");
		}
		else {
			relation = rootNode.putObject("related_event");
			setEventTag(relation, resultSet);
		}
		
		// related_shop
		if ( resultSet.getString("shop_id")  == null ){
			rootNode.putNull("related_shop");
		}
		else {
			relation = rootNode.putObject("related_shop");
			setShopTag(relation, resultSet);
		}
		
		// related_exhibition
		if ( resultSet.getString("exhibition_id")  == null ){
			rootNode.putNull("related_exhibition");
		}
		else {
			relation = rootNode.putObject("related_exhibition");
			setExhibitionTag(relation, resultSet);
		}
		
		rootNode.put("is_important", resultSet.getString("is_important"));
		rootNode.put("text", resultSet.getString("text"));
		rootNode.put("image_resource", resultSet.getString("image_resource"));
		
		return new JsonObject(this.mapper, rootNode);
	}
	
	// topics
	public JsonArray toTopics(ResultSet topicsLinksResultSet, ResultSet nowEventsResultSet, ResultSet importantNewsResultSet) throws HokutosaiApiException, SQLException
	{
		ArrayNode rootNode = this.mapper.createArrayNode();
		ObjectNode element = null;
		
		// links
		while (topicsLinksResultSet.next())
		{
			element = rootNode.addObject();
			
			element.put("title", topicsLinksResultSet.getString("title"));
			element.put("image_resource", topicsLinksResultSet.getString("image_resource"));
			element.put("link", topicsLinksResultSet.getString("link"));
			element.putNull("event_id");
			element.putNull("news_id");
		}
		
		// now events
		while (nowEventsResultSet.next())
		{
			element = rootNode.addObject();
			
			element.put("title", nowEventsResultSet.getString("title"));
			element.put("image_resource", nowEventsResultSet.getString("image_resource"));
			element.putNull("link");
			element.put("event_id", nowEventsResultSet.getInt("event_id"));
			element.putNull("news_id");
		}
		
		// important news
		while (importantNewsResultSet.next())
		{
			element = rootNode.addObject();
			
			element.put("title", importantNewsResultSet.getString("title"));
			element.put("image_resource", importantNewsResultSet.getString("image_resource"));
			element.putNull("link");
			element.putNull("event_id");
			element.put("news_id", importantNewsResultSet.getInt("news_id"));
		}
		
		return new JsonArray(this.mapper, rootNode);
	}
	
	// school_bus_timetables (Array)
	public JsonArray toSchoolBusTimetables(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		ArrayNode rootNode = this.mapper.createArrayNode();
		ObjectNode element = null;
		
		while(resultSet.next())
		{
			element = rootNode.addObject();
			
			element.put("departure_place", resultSet.getString("departure_place"));
			element.put("departure_time", resultSet.getString("departure_time"));
			element.put("arrive_place", resultSet.getString("arrive_place"));
			element.put("arrive_time", resultSet.getString("arrive_time"));
		}
		
		return new JsonArray(this.mapper, rootNode);
	}
	
	// places (Array)
	public JsonArray toPlacesArray(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		ArrayNode rootNode = this.mapper.createArrayNode();
		ObjectNode element = null;
		
		while(resultSet.next())
		{
			element = rootNode.addObject();
			
			element.put("place_id", resultSet.getString("place_id"));
			element.put("name", resultSet.getString("name"));
		}
		
		return new JsonArray(this.mapper, rootNode);
	}
	
	// placeを設定します。
	private void setPlace(ObjectNode placeNode, ResultSet resultSetPlace) throws SQLException
	{
		placeNode.put("place_id", resultSetPlace.getInt("place_id"));
		placeNode.put("name", resultSetPlace.getString("place_name"));
	}
	
	// auth_statuses
	public JsonObject toAuthStatuses(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		if( !resultSet.first() ){
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_FOUND_RECORD);
		}
		
		ObjectNode rootNode = this.mapper.createObjectNode();
		
		rootNode.put("user_id", resultSet.getString("user_id"));
		rootNode.put("role", resultSet.getString("role"));
		rootNode.put("permissions", resultSet.getString("permissions"));
		
		return new JsonObject(this.mapper, rootNode);
	}
	
	// api_user_credentials
	public JsonObject toApiUserCredentials(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		if( !resultSet.first() ){
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_FOUND_RECORD);
		}
		
		ObjectNode rootNode = this.mapper.createObjectNode();
		
		rootNode.put("user_id", resultSet.getString("user_id"));
		rootNode.put("key_token", resultSet.getString("key_token"));
		
		return new JsonObject(this.mapper, rootNode);
	}
	
	// oauth_bearer_token
	public JsonObject toOAuthBearerToken(ResultSet resultSet) throws HokutosaiApiException, SQLException
	{
		if( !resultSet.first() ){
			throw new HokutosaiApiException(HokutosaiApiException.ER_NOT_FOUND_RECORD);
		}
		
		ObjectNode rootNode = this.mapper.createObjectNode();
		
		rootNode.put("token", resultSet.getString("token"));
		
		return new JsonObject(this.mapper, rootNode);
	}
	
}
