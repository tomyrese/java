package iuh.fit.se.objectmodelapi.writer;
import java.io.StringWriter;
import java.util.Collections;

import iuh.fit.se.entities.Employee;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonWriter;
import jakarta.json.stream.JsonGenerator;

public class JavaObjectToJsonDemo {

	public static void main(String[] args) {
		Employee employee = new Employee(1L, "John Nguyen", 1000d);
		
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();


		JsonObject jsonObject = jsonObjectBuilder
				.add("id", employee.getId())
				.add("name", employee.getName())
				.add("salary", employee.getSalary())
				.build();
		
//		System.out.println(jsonObject.toString());
		
		// Get the JSON string from the JsonObject
		// Create a StringWriter to store the formated JSON
		StringWriter stringWriter = new StringWriter();
		
		// Create a JsonWriter with pretty printing (indentation) configuration
		JsonWriter jsonWriter = Json
//				.createWriterFactory(
//						Collections.singletonMap(JsonGenerator.PRETTY_PRINTING, true)
//				)
				.createWriter(stringWriter);
		jsonWriter.write(jsonObject);
		jsonWriter.close();
				
		System.out.println(stringWriter);
	}

}
