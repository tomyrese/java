package iuh.fit.se.objectmodelapi.writer;

import iuh.fit.se.entities.Employee;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JavaArrayToJsonDemoWriteFile {

	public static void main(String[] args) throws Exception {
		Employee emp1 = new Employee(10001, "John Smith", 10000d);
		Employee emp2 = new Employee(10002, "Tom Cruise", 20000d); 
		
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.add(emp1);
		employees.add(emp2);
		
		String js = generateArrayJson(employees);
		System.out.println("Employees:");
        System.out.println(js);
		export("json/employees.json", js);
	}

	private static void export(String filePath, String json) throws Exception{
		PrintWriter out = new PrintWriter(new FileOutputStream(filePath),true);
		out.println(json);
		out.close();
	}

	private static String generateArrayJson(ArrayList<Employee> employees) {
		
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		for(Employee e:employees) {
			JsonObject jsonObject = Json.createObjectBuilder()
					.add("id", e.getId())
					.add("name", e.getName())
					.add("salary", e.getSalary())
					.build();
			jsonArrayBuilder.add(jsonObject);
		}
		
		// Build the Orders JSON array
        JsonArray employeeArray = jsonArrayBuilder.build();
		
		return employeeArray.toString();
	}

}

