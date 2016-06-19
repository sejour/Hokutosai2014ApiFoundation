package hokutosai.api.foundation.json;

import hokutosai.api.foundation.HokutosaiApiException;
import hokutosai.api.foundation.sql.SelectQuery;

// 「Hokutosai API」で定義されているJSONオブジェクトのフィールドを「Hokutosai Database」の定義に従って射影するSELECT句を提供するクラス
public class ResultSetSelectQuery {

	private static final String SELECT_EVENTS_ARRAY = "SELECT events.event_id AS event_id, events.title AS title, events.date AS date, events.start_time AS start_time, events.end_time AS end_time, places.place_id AS place_id, places.name AS place_name, events.participants AS participants, events.detail AS detail, events.image_resource AS image_resource FROM events";
	private static final String INNER_JOIN_EVENTS_ARRAY = "INNER JOIN places ON events.place_id = places.place_id";
	
	// events (array) を射影するためのクエリを取得します。
	public static SelectQuery getEventsArray() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_EVENTS_ARRAY, INNER_JOIN_EVENTS_ARRAY);
	}
	
	private static final String SELECT_EVENT = "SELECT events.event_id AS event_id, events.title AS title, events.date AS date, events.start_time AS start_time, events.end_time AS end_time, places.place_id AS place_id, places.name AS place_name, events.participants AS participants, events.detail AS detail, events.image_resource AS image_resource FROM events";
	private static final String INNER_JOIN_EVENT = "INNER JOIN places ON events.place_id = places.place_id";
	
	// event を射影するためのクエリを取得します。
	public static SelectQuery getEvent() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_EVENT, INNER_JOIN_EVENT);
	}
	
	private static final String SELECT_EVENTS_PLACES = "SELECT DISTINCT places.place_id AS place_id, places.name AS name FROM places";
	private static final String INNER_JOIN_EVENTS_PLACES = "INNER JOIN events ON places.place_id = events.place_id";
	
	// events_places を射影するためのクエリを取得します。
	public static SelectQuery getEventsPlaces() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_EVENTS_PLACES, INNER_JOIN_EVENTS_PLACES);
	}
	
	private static final String SELECT_SHOPS_ARRAY = "SELECT shop_id, name, tenant, sales, image_resource FROM shops";
	
	// shops (array) を射影するためのクエリを取得します。
	public static SelectQuery getShopsArray() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_SHOPS_ARRAY);
	}
	
	private static final String SELECT_SHOP = "SELECT shops.shop_id AS shop_id, shops.name AS name, shops.tenant AS tenant, shops.sales AS sales, shops.place_id AS place_id, shops.introduction AS introduction, shops.image_resource AS image_resource, shops.menus_extension AS menus_extension, shops_scores.total_score AS total_score, shops_scores.assessed_count AS assessed_count FROM shops";
	private static final String INNER_JOIN_SHOP = "INNER JOIN shops_scores ON shops.shop_id = shops_scores.shop_id";
	
	// shop を射影するためのクエリを取得します。
	public static SelectQuery getShop() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_SHOP, INNER_JOIN_SHOP);
	}
	
	private static final String SELECT_MENUS = "SELECT item_id, name, price FROM shops_menus";
	
	// menus を射影するためのクエリを取得します。
	public static SelectQuery getMenus() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_MENUS);
	}
	
	private static final String SELECT_SHOP_ASSESS = "SELECT shop_id, total_score, assessed_count FROM shops_scores";
	
	// shop_assess を射影するためのクエリを取得します。
	public static SelectQuery getShopAssess() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_SHOP_ASSESS);
	}
	
	private static final String SELECT_EXHIBITIONS_ARRAY = "SELECT exhibitions.exhibition_id AS exhibition_id, exhibitions.title AS title, exhibitions.exhibitors AS exhibitors, exhibitions.displays AS displays, places.place_id AS place_id, places.name AS place_name, exhibitions.detail AS detail, exhibitions.image_resource AS image_resource FROM exhibitions";
	private static final String INNER_JOIN_EXHIBITIONS_ARRAY = "INNER JOIN places ON exhibitions.place_id = places.place_id";
	
	// exhibitions (array) を射影するためのクエリを取得します。
	public static SelectQuery getExhibitionsArray() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_EXHIBITIONS_ARRAY, INNER_JOIN_EXHIBITIONS_ARRAY);
	}
	
	private static final String SELECT_EXHIBITION = "SELECT exhibitions.exhibition_id AS exhibition_id, exhibitions.title AS title, exhibitions.exhibitors AS exhibitors, exhibitions.displays AS displays, places.place_id AS place_id, places.name AS place_name, exhibitions.detail AS detail, exhibitions.image_resource AS image_resource FROM exhibitions";
	private static final String INNER_JOIN_EXHIBITION = "INNER JOIN places ON exhibitions.place_id = places.place_id";
	
	// exhibition を射影するためのクエリを取得します。
	public static SelectQuery getExhibition() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_EXHIBITION, INNER_JOIN_EXHIBITION);
	}
	
	private static final String SELECT_CONTEST_STATUSES = "SELECT entrys_status, voting_status, results_status FROM statuses";

	// contest_statuses を射影するためのクエリを取得します。
	public static SelectQuery getContestStatuses() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_CONTEST_STATUSES);
	}
	
	private static final String SELECT_CONTESTS_ARRAY = "SELECT section_id, section_name, detail FROM contests";

	// contests (array) を射影するためのクエリを取得します。
	public static SelectQuery getContestsArray() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_CONTESTS_ARRAY);
	}
	
	private static final String SELECT_CONTEST = "SELECT section_id, section_name, detail FROM contests";

	// contest を射影するためのクエリを取得します。
	public static SelectQuery getContest() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_CONTEST);
	}
	
	private static final String SELECT_ENTRYS_ARRAY = "SELECT entrys.section_id AS section_id, contests.section_name AS section_name, entrys.entry_number AS entry_number, entrys.name AS entry_name, entrys.belong AS belong, entrys.profile AS profile, entrys.image_resource AS image_resource, entrys.result AS result FROM entrys";
	private static final String INNER_JOIN_ENTRYS_ARRAY = "INNER JOIN contests ON entrys.section_id = contests.section_id";

	// entrys (array) を射影するためのクエリを取得します。
	public static SelectQuery getEntrysArray() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_ENTRYS_ARRAY, INNER_JOIN_ENTRYS_ARRAY);
	}
	
	private static final String SELECT_ENTRY = "SELECT entrys.section_id AS section_id, contests.section_name AS section_name, entrys.entry_number AS entry_number, entrys.name AS entry_name, entrys.belong AS belong, entrys.profile AS profile, entrys.image_resource AS image_resource, entrys.result AS result FROM entrys";
	private static final String INNER_JOIN_ENTRY = "INNER JOIN contests ON entrys.section_id = contests.section_id";

	// entry を射影するためのクエリを取得します。
	public static SelectQuery getEntry() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_ENTRY, INNER_JOIN_ENTRY);
	}
	
	private static final String SELECT_CONTESTS_RESULTS_ARRAY = "SELECT results.section_id AS section_id, results.entry_number AS entry_number, entrys.name AS name, results.app_votes_count AS app_votes_count, results.paper_votes_count AS paper_votes_count, results.app_votes_count+results.paper_votes_count AS total, results.result FROM results";
	private static final String INNER_JOIN_CONTEST_RESULTS_ARRAY = "INNER JOIN entrys ON results.section_id = entrys.section_id AND results.entry_number = entrys.entry_number";

	// contests_results (array) を射影するためのクエリを取得します。
	public static SelectQuery getConstestResultsArray() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_CONTESTS_RESULTS_ARRAY, INNER_JOIN_CONTEST_RESULTS_ARRAY);
	}
	
	private static final String SELECT_CONTESTS_RESULT = "SELECT results.section_id AS section_id, results.entry_number AS entry_number, entrys.name AS name, results.app_votes_count AS app_votes_count, results.paper_votes_count AS paper_votes_count, results.app_votes_count+results.paper_votes_count AS total, results.result FROM results";
	private static final String INNER_JOIN_CONTEST_RESULT = "INNER JOIN entrys ON results.section_id = entrys.section_id AND results.entry_number = entrys.entry_number";

	// contests_result を射影するためのクエリを取得します。
	public static SelectQuery getContestsResult() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_CONTESTS_RESULT, INNER_JOIN_CONTEST_RESULT);
	}
	
	private static final String SELECT_NEWS_ARRAY = "SELECT news.news_id AS news_id, news.title AS title, news.send_datetime AS send_datetime, news.sender_department AS sender_department, events.event_id AS event_id, events.title AS event_title, shops.shop_id AS shop_id, shops.name AS shop_name, exhibitions.exhibition_id AS exhibition_id, exhibitions.title AS exhibition_title, news.is_important AS is_important, news.text AS text, news.image_resource AS image_resource FROM news";
	private static final String LEFT_JOIN_NEWS_ARRAY = "LEFT JOIN events USING(event_id) LEFT JOIN shops USING(shop_id) LEFT JOIN exhibitions USING(exhibition_id)";
	
	// news (array) を射影するためのクエリを取得します。
	public static SelectQuery getNewsArray() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_NEWS_ARRAY, LEFT_JOIN_NEWS_ARRAY);
	}
	
	private static final String SELECT_NEWS_DETAIL = "SELECT news.news_id AS news_id, news.title AS title, news.send_datetime AS send_datetime, news.sender_department AS sender_department, events.event_id AS event_id, events.title AS event_title, shops.shop_id AS shop_id, shops.name AS shop_name, exhibitions.exhibition_id AS exhibition_id, exhibitions.title AS exhibition_title, news.is_important AS is_important, news.text AS text, news.image_resource AS image_resource FROM news";
	private static final String LEFT_JOIN_NEWS_DETAIL = "LEFT JOIN events USING(event_id) LEFT JOIN shops USING(shop_id) LEFT JOIN exhibitions USING(exhibition_id)";

	// news_detail を射影するためのクエリを取得します。
	public static SelectQuery getNewsDetail() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_NEWS_DETAIL, LEFT_JOIN_NEWS_DETAIL);
	}
	
	private static final String SELECT_TOPICS_LINKS = "SELECT title, image_resource, link FROM topics_links";
	
	// topicsのtopicsLinksResultSet を射影するためのクエリを取得します。
	public static SelectQuery getTopicsLinks() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_TOPICS_LINKS);
	}
	
	private static final String SELECT_TOPICS_NOW_EVENTS = "SELECT event_id, title, image_resource FROM events";
	
	// topicsのnowEventsResultSet を射影するためのクエリを取得します。
	public static SelectQuery getTopicsNowEvents() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_TOPICS_NOW_EVENTS);
	}
	
	private static final String SELECT_TOPICS_IMPORTANT_NEWS = "SELECT news_id, title, image_resource FROM news";
		
	// topicsのimportantNewsResultSet を射影するためのクエリを取得します。
	public static SelectQuery getTopicsImportantNews() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_TOPICS_IMPORTANT_NEWS);
	}
	
	private static final String SELECT_SCHOOL_BUS_TIMETABLES = "SELECT departure_place, departure_time, arrive_place, arrive_time FROM school_bus_timetables";

	// school_bus_timetables を射影するためのクエリを取得します。
	public static SelectQuery getSchoolBusTimetables() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_SCHOOL_BUS_TIMETABLES);
	}
	
	private static final String SELECT_PLACES_ARRAY = "SELECT place_id, name FROM places";

	// places (array) を射影するためのクエリを取得します。
	public static SelectQuery getPlacesArray() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_PLACES_ARRAY);
	}
	
	private static final String SELECT_AUTH_STATUSES = "SELECT user_id, role, permissions FROM api_users";

	// auth_statuses を射影するためのクエリを取得します。
	public static SelectQuery getAuthStatuses() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_AUTH_STATUSES);
	}
	
	private static final String SELECT_API_USER_CREDENTIALS = "SELECT user_id, key_token FROM api_users";

	// api_user_credentials を射影するためのクエリを取得します。
	public static SelectQuery getApiUserCredentials() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_API_USER_CREDENTIALS);
	}
	
	private static final String SELECT_OAUTH_BEARER_TOKEN = "SELECT token FROM oauth2_bearer_tokens";
	
	// oauth_bearer_token を射影するためのクエリを取得します。
	public static SelectQuery getOAuthBearerToken() throws HokutosaiApiException
	{
		return new SelectQuery(SELECT_OAUTH_BEARER_TOKEN);
	}
	
}
