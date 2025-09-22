package iuh.fit.se;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import iuh.fit.se.entities.Employee;
import iuh.fit.se.utils.EmployeeBuilder;
import iuh.fit.se.utils.EmployeeWriter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.json.JsonObject;

@DisplayName("Demo JUnit5")
public class JsonDemoTest {
	
	/**
	 * @BeforeAll dùng để chỉ định test method chạy đầu tiên
	 * Nó phải được đặt là phương thức tĩnh (static), 
	 * nếu không chương trình sẽ không biên dịch được
	 */
	@BeforeAll
	static void setup() {
		System.out.println("Before ALL");
	}
	
	/**
	 * @BeforeEach chỉ định 1 method sẽ luôn được thực thi trước mỗi test method thực thi
	 */
    @BeforeEach
    public void beforeEach() {
        System.out.println("Before Each");
    }

    /**
     * @AfterAll Chỉ định method sẽ được thực thi khi tất cả các test method trong class thực thi xong
     * Nó phải được đặt trên static method
     */
    @AfterAll
    public static void afterAll() {
        System.out.println("After ALL");
    }
    @Test
	@DisplayName("whenEmployeeIsConVertedToJsonString_thenAvalidJsonStringIsReturned")
	void whenEmployeeIsConVertedToJsonString_thenAvalidJsonStringIsReturned() {
    	Employee emp = new Employee(10001, "John Smith", 10000d);
    	
    	JsonObject jsonObject = (new EmployeeWriter(emp)).convertToJsonObject();
		
		assertEquals(10001L, jsonObject.getJsonNumber("id").longValue(), "Id has expected value");
		assertEquals("John Smith", jsonObject.getString("name"), "Name has expected value");
		assertEquals(10000.0d, jsonObject.getJsonNumber("salary").doubleValue(), "Salary has expected value");
		
		System.out.println("whenEmployeeIsConVertedToJsonString_thenAvalidJsonStringIsReturned: PASSED");
	}
    
    @Test
	@DisplayName("whenJsonStringIsConVertedToEmployee_thenAvalidObjectIsReturned")
	void whenJsonStringIsConVertedToEmployee_thenAvalidObjectIsReturned() {
    	String json = "{\"id\":1,\"name\":\"John Nguyen\",\"salary\":1000.0}";
    	
    	Employee employee = (new EmployeeBuilder(json)).build();
		
		assertEquals(1L, employee.getId(), "Id has expected value");
		assertEquals("John Nguyen", employee.getName(), "Name has expected value");
		assertEquals(1000.0d, employee.getSalary(), "Salary has expected value");
		
		System.out.println("whenJsonStringIsConVertedToEmployee_thenAvalidObjectIsReturned: PASSED");
	}
    
    /**
     * disable() method không được thực thi vì bị chặn bởi @Disabled
     */
    @Test
    @Disabled
    public void disable() {
        System.out.println("Disabled");
    }
    
    @Test
    @DisplayName("assertNotEqualsExample")
    void assertNotEqualsExample(){
        //Test will failed
        assertNotEquals(5,2);

        //Test will pass
        assertNotEquals(4,5);
    }
}
