package provider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlConnection {
	private String dbURL;
	private String dbUsername;
	private String dbPassword;
	private ResultSet rs;
	private PreparedStatement statement;
	private String query;
	
	public sqlConnection() {
		this.dbURL = "jdbc:mysql://localhost:3306/medicloud";
		this.dbUsername = "medicloud";
		this.dbPassword = "medicloud";
	}
	
	public void setQuery(String sql) {
		this.query = sql;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

			this.statement = con.prepareStatement(sql);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public boolean execute() {
		try {
			this.rs = this.statement.executeQuery();
			
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	private int dt;
	
	public boolean executeData() {
		//this.dt = data;
		try {
			this.dt = this.statement.executeUpdate();
			
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	public void setString(int paramPosition, String paramValue) {
		try {
			this.statement.setString(paramPosition, paramValue);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setInt(int paramPosition, int paramValue) {
		try {
			this.statement.setInt(paramPosition, paramValue);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet getResultSet() {
		return this.rs;
	}
	public int getData(){
		return this.dt;
	}
}
