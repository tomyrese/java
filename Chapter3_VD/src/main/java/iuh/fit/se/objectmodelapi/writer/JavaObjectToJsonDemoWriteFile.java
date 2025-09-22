package iuh.fit.se.objectmodelapi.writer;

import iuh.fit.se.entities.Employee;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class JavaObjectToJsonDemoWriteFile {

	public static void main(String[] args) throws Exception {
		Employee emp = new Employee(10001, "John Smith", 10000d);
		
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("id", emp.getId())
				.add("name", emp.getName())
				.add("salary", emp.getSalary())
				.build();
		
		String filePath = "json/employee.json";
		PrintWriter out = new PrintWriter(new FileOutputStream(filePath),true);
		out.println(jsonObject.toString());
		out.close();
	}
}
