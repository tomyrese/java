package iuh.fit.se.utils;

import java.io.StringReader;

import iuh.fit.se.entities.Employee;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class EmployeeBuilder {
	
	private String jsonSring;
	
	public EmployeeBuilder(String jsonString) {
		this.jsonSring = jsonString;
	}
	
	public Employee build() {
		JsonReader reader = Json.createReader(new StringReader(this.jsonSring));
		JsonObject jsonObject = reader.readObject();
		
		Employee employee = new Employee(
				jsonObject.getJsonNumber("id").longValue(),
				jsonObject.getString("name"),
				jsonObject.getJsonNumber("salary").doubleValue()
		);
		
		return employee;
	}
}
