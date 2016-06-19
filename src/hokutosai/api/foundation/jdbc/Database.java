package hokutosai.api.foundation.jdbc;

import hokutosai.api.foundation.HokutosaiApiException;
import hokutosai.api.foundation.sql.DeleteQuery;
import hokutosai.api.foundation.sql.InsertQuery;
import hokutosai.api.foundation.sql.SelectQuery;
import hokutosai.api.foundation.sql.UpdateQuery;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Database {

	// コネクタドライバ名
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	// データベースの名前を取得します。
	public abstract String getDataBaseName();
	
	// コネクションユーザーのユーザー名を取得します。
	public abstract String getConnectionUserName();
	
	// コネクションユーザーのユーザーパスを取得します。
	protected abstract String getConnectionUserPass();
	
	// Unicodeを使用するかどうか。
	public abstract boolean isUseUnicode();
	
	// 使用する文字セットを取得します。
	public abstract String getCharSet();
	
	// データベースのコネクション
	private Connection connection;
	
	// データベースへのコネクションを確立します。
	public void connectDatabase() throws ClassNotFoundException, SQLException, HokutosaiApiException
	{
		if (this.connection != null){
			throw new HokutosaiApiException(50000);
		}
		
		//URL生成
		String connectionUrl = String.format("jdbc:mysql://localhost/%s?user=%s&password=%s&useUnicode=%s&characterEncoding=%s", this.getDataBaseName(), this.getConnectionUserName(), this.getConnectionUserPass(), String.valueOf(this.isUseUnicode()), this.getCharSet());
		
		//ドライバのロード
		Class.forName(DRIVER_NAME);
		
		//データベースに接続
		this.connection = DriverManager.getConnection(connectionUrl);
	}
	
	// データベースのコネクションを取得します。
	public Connection getConnection() throws HokutosaiApiException
	{
		if( this.connection == null ){
			throw new HokutosaiApiException(50000);
		}
		
		return this.connection;
	}
	
	// SQLステートメントを生成します。
	public Statement createStatement() throws SQLException
	{
		return this.connection.createStatement();
	}
	
	// SQLステートメントをプリペアします。
	public PreparedStatement prepareStatement(String sql) throws SQLException
	{
		return this.connection.prepareStatement(sql);
	}
	
	// ストアドプロシージャステートメントをプリペアします。
	public CallableStatement prepareCall(String sql) throws SQLException
	{
		return this.connection.prepareCall(sql);
	}
	
	// SELECT句から成るSQLクエリを実行してResultSetを取得します。
	public ResultSet executeQuery(String sql) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(sql);
		
		return statement.executeQuery();
	}
	
	// SELECT句から成るのSQLクエリを実行します。
	public ResultSet executeQuery(SelectQuery sql) throws SQLException, HokutosaiApiException
	{
		PreparedStatement statement = sql.generateStatement(this.connection);
		
		return statement.executeQuery();
	}

	// SELECT句以外のSQLクエリを実行します。
	public void executeUpdate(String sql) throws SQLException, HokutosaiApiException
	{
		PreparedStatement statement = this.connection.prepareStatement(sql);
		
		if ( statement.executeUpdate() == 0 ) {
			throw new HokutosaiApiException(40000);
		}
	}
	
	// UPDATE句から成るSQLクエリを実行します。
	public void executeUpdate(UpdateQuery sql) throws SQLException, HokutosaiApiException
	{
		PreparedStatement statement = sql.generateStatement(this.connection);
		
		if ( statement.executeUpdate() == 0 ) {
			throw new HokutosaiApiException(40000);
		}
	}
	
	// INSERT句から成るSQLクエリを実行します。
	public void executeUpdate(InsertQuery sql) throws SQLException, HokutosaiApiException
	{
		PreparedStatement statement = sql.generateStatement(this.connection);
		
		if ( statement.executeUpdate() == 0 ) {
			throw new HokutosaiApiException(40000);
		}
	}
	
	// DELETE句から成るSQLクエリを実行します。
	public void executeUpdate(DeleteQuery sql) throws SQLException, HokutosaiApiException
	{
		PreparedStatement statement = sql.generateStatement(this.connection);
		
		if ( statement.executeUpdate() == 0 ) {
			throw new HokutosaiApiException(40000);
		}
	}
	
	// データベースをコミットします。
	public void commit() throws SQLException
	{
		this.connection.commit();
	}
	
	// データベースへのコネクションを破棄します。
	public void close() throws SQLException
	{
		if( this.connection != null ){
			this.connection.close();
			this.connection = null;
		}
	}
	
	// データベースのコネクションが確立されているかどうか
	public boolean isConnected()
	{
		return this.connection != null;
	}
	
}

