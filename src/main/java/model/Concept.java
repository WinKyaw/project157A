package model;

import java.sql.ResultSet;

import provider.sqlConnection;

public class Concept {
	private int concept_id;
	private String description;
	
	public Concept(int id) {
		String sql = "SELECT concept_id, description "
						+ "FROM concept WHERE concept_id = ? ";
		
		sqlConnection con = new sqlConnection();
		con.setQuery(sql);
		con.setInt(1, id);
		
		if (con.execute()) {
			try {
				ResultSet rs = con.getResultSet();
				
				if (rs.next()) {
					this.concept_id = rs.getInt("concept_id");
					this.description = rs.getString("description");
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			System.out.println("Sql did not execute successfully");
		}
	}
}
