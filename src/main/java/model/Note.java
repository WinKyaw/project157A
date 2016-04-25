package model;
import java.sql.ResultSet;

import provider.sqlConnection;

public class Note {
	private int note_id;
	private int patient_id;
	private int encounter_id;
	private int ob_id;
	private String message;
	private String date_created;
	
	public Note(int id) {
		String sql = "SELECT note_id, patient_id, encounter_id, ob_id, message, date_created "
						+ "FROM note WHERE note_id = ? ";
		
		sqlConnection con = new sqlConnection();
		con.setQuery(sql);
		con.setInt(1, id);
		
		if (con.execute()) {
			try {
				ResultSet rs = con.getResultSet();
				
				if (rs.next()) {
					this.note_id = rs.getInt("note_id");
					this.patient_id = rs.getInt("patient_id");
					this.encounter_id = rs.getInt("encounter_id");
					this.ob_id = rs.getInt("ob_id");
					this.message = rs.getString("message");
					this.date_created = rs.getString("date_created");
				}
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
		} else {
			System.out.println("Sql did not execute successfully");
		}
	}
	
	public static boolean insert(int patientID, String newMessage) {
		String sql ="INSERT INTO note (patient_id, message, encounter_id, ob_id, date_created) VALUES ( ?, ?, 0, 0, NOW());";

		sqlConnection con = new sqlConnection();
		con.setQuery(sql);
		con.setInt(1, patientID);
		con.setString(2, newMessage);
		
		if (con.executeData()) {
			
			return true;
		} else {
			System.out.println("Sql did not execute successfully");
			
			return false;
		}
	}
}
