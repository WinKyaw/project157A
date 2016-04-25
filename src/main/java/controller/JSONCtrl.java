package controller;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import model.ActiveItem;
import model.Note;
import model.Patient;
import model.PatientListPersonal;

@RestController
public class JSONCtrl {
	
	@RequestMapping("/patients/personal")
	public String patients_personal(){
		PatientListPersonal plp = new PatientListPersonal();
		
		JSONArray json = plp.getData();
		return json.toString();
		
	}
	
	@RequestMapping(value="/patient/{patient_id}/personal")
	public String patient_personal(@PathVariable("patient_id") int patient_id) throws JsonProcessingException {
		Patient pa = new Patient(patient_id);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
		
		String json = ow.writeValueAsString(pa);
		return json;
	}
	
	@RequestMapping(value="/patient/{patient_id}/active")
	public String patient_active(@PathVariable("patient_id") int patient_id) throws JsonProcessingException {
		Patient pa = new Patient(patient_id);
		List<ActiveItem> list = pa.fetchActiveList();
		
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
		
		String json = ow.writeValueAsString(pa);
		return json;
	}
	
	@RequestMapping(value="/patient/new", method=POST)
	public boolean patient_new(@RequestBody Map<String, Object> payload) {
		System.out.println(payload.get("first_name"));
		return Patient.insert(payload.get("first_name").toString(), 
				payload.get("last_name").toString(), 
				payload.get("birthdate").toString().split("T")[0], 
				payload.get("gender").toString(), 
				payload.get("email").toString(), 
				payload.get("phone").toString());
	}
	
	@RequestMapping(value="/patient/note/new", method=POST)
	public boolean patient_note_new(@RequestBody Map<String, Object> payload) {
		return Note.insert((int) Integer.valueOf(payload.get("patient_id").toString()), payload.get("message").toString());
	}
	
	@RequestMapping(value="/patient/active/new", method=POST)
	public boolean patient_active_new(@RequestBody Map<String, Object> payload) {
		System.out.println(payload);
		
		return ActiveItem.insert((int) Integer.valueOf(payload.get("patient_id").toString()), 
				(int) Integer.valueOf(payload.get("concept_id").toString()),
				payload.get("comments").toString());
	}
}
