package model;
import java.sql.*;

import provider.sqlConnection;

public class Person {

	private int person_id;
	private String first_name;
	private String last_name;
	private String birthdate;
	private String gender;
	private String ssn;

	public Person(int person_id) {
		String sql = "SELECT person_id, first_name, last_name, birthdate, gender, ssn "
						+ "FROM person WHERE person_id = ? ";

		sqlConnection con = new sqlConnection();
		con.setQuery(sql);
		con.setInt(1, person_id);
		
		if (con.execute()) {
			try {
				ResultSet rs = con.getResultSet();
				
				if (rs.next()) {
					this.person_id = rs.getInt("person_id");
					this.first_name = rs.getString("first_name");
					this.last_name = rs.getString("last_name");
					this.birthdate = rs.getString("birthdate");
					this.gender = rs.getString("gender");
					this.ssn = rs.getString("ssn");
				}
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
		} else {
			System.out.println("Sql did not execute successfully");
		}
	}
		   
	public void update() {
	  sqlConnection sql = new sqlConnection();

	  
	   String insertInto = "Update person set person_id = ?, first_name = ?, last_name=?, gender= ?, birthdate = ?, ssn =? )" 		
				 ;    		   
	   sql.setQuery(insertInto);
	   sql.setInt(1, person_id);
	   sql.setString(2, first_name);
	   sql.setString(3, last_name);
	   sql.setString(4, gender);
	   sql.setString(5, birthdate);
	   sql.setString(6, ssn);
	   sql.executeData();
	   
	}
	
	public static void insert(String fn, String ln, String birth, 
			String gender, String ssn) {
		sqlConnection sql = new sqlConnection();

		System.out.println("HELLO!");
		String insertInto = "INSERT INTO person (first_name, last_name, gender, birthdate, ssn )" + 		
							"values( ? , ? , ?, ?, ?, ?) " ;    		   
		sql.setQuery(insertInto);
		sql.setString(2, fn);
		sql.setString(3, ln);
		sql.setString(4, gender);
		sql.setString(5, birth);
		sql.setString(6, ssn);
		sql.executeData();
	}
}
	      
	
	

