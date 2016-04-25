package model;

import java.sql.ResultSet;

import provider.sqlConnection;

public class Contact {
	private int contact_id;
	private int person_id;
	private String email;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zip;
	
	public Contact(int contact_id) {
		String sql = "SELECT contact_id, person_id, email, phone, address, city, state, zip "
						+ "FROM contact WHERE contact_id = ? ";
		
		sqlConnection con = new sqlConnection();
		con.setQuery(sql);
		con.setInt(1, contact_id);
		
		if (con.execute()) {
			try {
				ResultSet rs = con.getResultSet();
				
				if (rs.next()) {
					this.contact_id = rs.getInt("contact_id");
					this.person_id = rs.getInt("person_id");
					this.email = rs.getString("email");
					this.phone = rs.getString("phone");
					this.address = rs.getString("address");
					this.city = rs.getString("city");
					this.state = rs.getString("state");
					this.zip = rs.getString("zip");
				}
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
		} else {
			System.out.println("Sql did not execute successfully");
		}
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getFullAddress() {
		return this.address + ", "  + this.city + ", " + this.state + " " + this.zip;
	}
}
