package iuh.fit.se;

import static org.junit.jupiter.api.Assertions.assertEquals;

import iuh.fit.se.entities.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JacksonDemoTest {
	@Test
	@DisplayName("whenEmployeeIsConVertedToJsonString_thenAvalidJsonStringIsReturned")
	void whenEmployeeIsConVertedToJsonString_thenAvalidJsonStringIsReturned() {
		
		String employeeJson ="{\"id\":1,\"name\":\"John Smith\",\"salary\":1000.0}";
		ObjectMapper objectMapper = new ObjectMapper();
		Employee employee;
        try {
			employee = objectMapper.readValue(employeeJson, Employee.class);
			assertEquals(1L, employee.getId(), "Id has expected value");
			assertEquals("John Smith", employee.getName(), "Name has expected value");
			assertEquals(1000.0d, employee.getSalary(), "Salary has expected value");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      		
		System.out.println("whenEmployeeIsConVertedToJsonString_thenAvalidJsonStringIsReturned: PASSED");
	}
}
