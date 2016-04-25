package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import provider.sqlConnection;

public class Patient {
	private int patient_id;
	private Person personal;
	private Contact contact;
	private List<Note> notes;
	private List<ActiveItem> active_list;
	
	public Patient(int patient_id) {
		this.patient_id = patient_id;
		this.notes = new ArrayList<Note>();
		this.active_list = new ArrayList<ActiveItem>();
		
		this.fetchPersonal();
		this.fetchContact();
		this.fetchNotes();
	}
	
	public static boolean insert(String fn, String ln, String birth, String gender, String email, String phone) {
		String sql ="CALL insertNewPatient(?, ?, ?, ?, ?, ?);";

		sqlConnection con = new sqlConnection();
		con.setQuery(sql);
		con.setString(1, fn);
		con.setString(2, ln);
		con.setString(3, birth);
		con.setString(4, gender);
		con.setString(5, email);
		con.setString(6, phone);
		
		if (con.executeData()) {
			
			return true;
		} else {
			System.out.println("Sql did not execute successfully");
			
			return false;
		}
	}
	
	private void fetchNotes() {
		String sql = "SELECT note_id FROM note WHERE patient_id = ? ORDER BY date_created DESC";

		sqlConnection con = new sqlConnection();
		con.setQuery(sql);
		con.setInt(1, patient_id);
		
		if (con.execute()) {
			try {
				ResultSet rs = con.getResultSet();
				
				while (rs.next()) {
					Note n = new Note(rs.getInt("note_id"));
					this.notes.add(n);
				}
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
		} else {
			System.out.println("Sql did not execute successfully");
		}
	}
	
	private void fetchPersonal() {
		String sql = "SELECT person_id FROM patient WHERE patient_id = ? ";

		sqlConnection con = new sqlConnection();
		con.setQuery(sql);
		con.setInt(1, patient_id);
		
		if (con.execute()) {
			try {
				ResultSet rs = con.getResultSet();
				
				if (rs.next()) {
					Person p = new Person(rs.getInt("person_id"));
					this.personal = p;
				}
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
		} else {
			System.out.println("Sql did not execute successfully");
		}
	}
	
	private void fetchContact() {
		String sql = "SELECT contact_id FROM contact WHERE person_id IN " +
					"(SELECT person_id FROM patient WHERE patient_id = ?) ";

		sqlConnection con = new sqlConnection();
		con.setQuery(sql);
		con.setInt(1, patient_id);
		
		if (con.execute()) {
			try {
				ResultSet rs = con.getResultSet();
				
				if (rs.next()) {
					
					Contact c = new Contact(rs.getInt("contact_id"));
					this.contact = c;
				}
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
		} else {
			System.out.println("Sql did not execute successfully");
		}
	}
	
	public List<ActiveItem> fetchActiveList() {
		String sql = "SELECT active_id FROM active_list WHERE patient_id = ? ";

		sqlConnection con = new sqlConnection();
		con.setQuery(sql);
		con.setInt(1, patient_id);
		
		if (con.execute()) {
			try {
				ResultSet rs = con.getResultSet();
				
				while (rs.next()) {
					ActiveItem ai = new ActiveItem(rs.getInt("active_id"));
					this.active_list.add(ai);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			System.out.println("Sql did not execute successfully");
		}
		
		return this.active_list;
	}
}

