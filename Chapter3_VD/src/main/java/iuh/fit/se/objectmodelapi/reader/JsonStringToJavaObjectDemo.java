package iuh.fit.se.objectmodelapi.reader;

import iuh.fit.se.entities.Employee;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.StringReader;

public class JsonStringToJavaObjectDemo {

	public static void main(String[] args) {
		String json = "{\"id\":1,\"name\":\"John Nguyen\",\"salary\":1000.0}";

		JsonReader reader = Json.createReader(new StringReader(json));
		JsonObject jsonObject = reader.readObject();


		Employee employee = new Employee(
				jsonObject.getJsonNumber("id").longValue(),
				jsonObject.getString("name"),
				jsonObject.getJsonNumber("salary").doubleValue()
		);

		System.out.println(employee);
	}

}