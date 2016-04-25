package model;

import java.sql.ResultSet;
import java.util.List;

import org.json.JSONArray;

import provider.sqlConnection;
import provider.JSONConverter;

public class PatientListPersonal {
	private ResultSet resultSet;
	private String sql;
	private sqlConnection connection;
	
	public PatientListPersonal() {
		this.sql = "SELECT * FROM vw_patients_personal";
				
		this.connection = new sqlConnection();
	}
	
	public JSONArray getData() {
		this.connection.setQuery(sql);
		JSONArray data = null;
				
		if (connection.execute()) {
			try {
				this.resultSet = connection.getResultSet(); 
				data = JSONConverter.convertToJSON(resultSet);
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
				System.out.println("An error occurred getting resultset as json");
			}
			
		} else {
			System.out.println("Sql did not execute successfully");
		}
		
		return data;
	}
}
