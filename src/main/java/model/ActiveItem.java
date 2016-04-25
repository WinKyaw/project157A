package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import provider.sqlConnection;

public class ActiveItem {
	private int active_id;
	private int patient_id;
	private Observation start_observation;
	private Observation end_observation;
	private Concept concept;
	
	public ActiveItem(int id) {
		this.active_id = id;
		
		String sql = "SELECT concept_id, start_obs_id, end_obs_id FROM active_list WHERE active_id = ? ";

		sqlConnection con = new sqlConnection();
		con.setQuery(sql);
		con.setInt(1, active_id);
		
		if (con.execute()) {
			try {
				ResultSet rs = con.getResultSet();
				
				while (rs.next()) {
					Concept c = new Concept(rs.getInt("concept_id"));
					Observation bob = new Observation(rs.getInt("start_obs_id"));
					Observation eob = new Observation(rs.getInt("end_obs_id"));
					
					this.concept = c;
					this.start_observation = bob;
					this.end_observation = eob;
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			System.out.println("Sql did not execute successfully");
		}
	}
	
	public static boolean insert(int patientID, int conceptID, String comments) {
		String sql ="CALL insertActiveItem( ?, ?, ?);";

		sqlConnection con = new sqlConnection();
		con.setQuery(sql);
		con.setInt(1, patientID);
		con.setInt(2, conceptID);
		con.setString(3, comments);
		
		if (con.executeData()) {
			
			return true;
		} else {
			System.out.println("Sql did not execute successfully");
			
			return false;
		}
	}
}
