package model;
import java.sql.*;

import provider.sqlConnection;

public class Observation {

	private int ob_id;
	private String comments;

	public Observation(int obs_id){
		sqlConnection sql = new sqlConnection();
		   
	   String selectFrom = "Select ob_id, comments from observation where ob_id = ?";
	   sql.setQuery(selectFrom);
	   sql.setInt(1, obs_id);
	   sql.execute();
	   ResultSet rs = sql.getResultSet();
	   try {
			if (rs.next()){
				this.comments = rs.getString("comments");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
   public static void insert(String comments) {
	  sqlConnection sql = new sqlConnection();

	   String insertInto = "INSERT INTO observation (comments)" + 		
				"values(?)" ;    		   
	   sql.setQuery(insertInto);
	   sql.setString(1, comments);
	   
	   sql.executeData();
	   
	}
}
	      
	
	

