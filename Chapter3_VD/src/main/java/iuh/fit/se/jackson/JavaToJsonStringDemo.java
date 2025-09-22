package iuh.fit.se.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.se.entities.Employee;

public class JavaToJsonStringDemo {

	
	private static String json;
	public static void main(String[] args) {
		ObjectMapper objectMapper = new ObjectMapper();
		Employee employee=new Employee(1L, "John Smith", 1000d);
		
		try {
			json = objectMapper.writeValueAsString(employee);
			System.out.println(json);  
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
