package hokutosai.api.foundation.jdbc;

public class Hokutosai2014ContestsDatabase extends Database{

	private static final String DATABASE_NAME = "hokutosai_2014_contests";
	
	private static final String CONNECTION_USERNAME = "hokutosai_api";
	
	private static final String CONNECTION_USERPASS = "2rN76O5p01sR34gD92T6o8ts185Kt05v";
	
	private static final String CHAR_SET = "utf-8";
	
	@Override
	public String getDataBaseName() {
		// TODO 自動生成されたメソッド・スタブ
		return DATABASE_NAME;
	}

	@Override
	public String getConnectionUserName() {
		// TODO 自動生成されたメソッド・スタブ
		return CONNECTION_USERNAME;
	}

	@Override
	protected String getConnectionUserPass() {
		// TODO 自動生成されたメソッド・スタブ
		return CONNECTION_USERPASS;
	}

	@Override
	public boolean isUseUnicode() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public String getCharSet() {
		// TODO 自動生成されたメソッド・スタブ
		return CHAR_SET;
	}
	
}

