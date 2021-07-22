package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    /*Given - Prepare Request
      When - Make Request/Call; Hit endpoint
      Then - Verification/Assertion
     */

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjY2MTc5MjIsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYyNjY2MTEyMiwidXNlcklkIjoiMjkzOSJ9.F4KdIiXqWMbfabBQvPoJfVj2JiTmpb5EqUjqC-LfVGA";
    static String employee_id;

    /*GIVEN
    getting employee info vvv
    printing info as string instead of JSON
    */

    @Test
    public void sampleTest() {

        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json").queryParam("employee_id", "20158A");

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        System.out.println(response.asString());

    }

    /*
    WHEN
    creating employee and printing response as string vvv
     */

    @Test
    public void aPostCreateEmployee() {
        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json").body("{\n" +
                "  \"emp_firstname\": \"Estela\",\n" +
                "  \"emp_lastname\": \"Vazquez\",\n" +
                "  \"emp_middle_name\": \"A\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"1997-09-03\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Automation Engineer\"\n" +
                "}");
        Response response = preparedRequest.when().post("/createEmployee.php");

        //same as sout(response.asString());
        //response.prettyPrint();

        /*
        jsonPath() allows us to retrieve specific data from a json object - just like xpath w/ selenium
         */

        employee_id = response.jsonPath().getString("Employee.employee_id");

        // System.out.println(employee_id);

        //Assertions
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Message", equalTo("Employee Created"));//using hamcrest matchers class
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Estela"));//using hamcrest matchers class
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
    }

    @Test
    public void bGetCreatedEmployee() {
        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-type", "application/json").queryParam("employee_id", employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        response.prettyPrint();

        String empID = response.jsonPath().getString("employee.employee_id");

        boolean comparingEmpIDs = empID.contentEquals(employee_id);

        Assert.assertTrue(comparingEmpIDs);

        String firstName = response.jsonPath().getString("employee.emp_firstname");

        Assert.assertTrue(firstName.contentEquals("Estela"));

    }

    //@Test
    public void cGetAllEmployees() {

        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-type", "application/json");

        Response response = preparedRequest.when().get("/getAllEmployees.php");

        String allEmployees = response.prettyPrint();

        JsonPath js = new JsonPath(allEmployees);

        int count = js.getInt("Employees.size()");

        System.out.println(count);

        //Print out all employee IDs from response

        for (int i = 0; i < count; i++) {
            String employeeIDs = js.getString("Employees[" + i + "].employee_id");
            //System.out.println(employeeIDs);

            if (employeeIDs.contentEquals(employee_id)) {
                System.out.println("Employee ID " + employee_id + " is present in response body");
                String firstName = js.getString("Employees[" + i + "].emp_firstname");

                System.out.println("Employee name is " + firstName);
                break;
            }
        }
    }

    @Test
    public void dPutUpdateCreatedEmployee() {
        RequestSpecification preparedRequest = given().header("Authorization", token).
                header("Content-Type", "application/json").body("{\n" +
                "  \"employee_id\": \"" + employee_id + "\",\n" +
                "\"emp_firstname\": \"Stela\",\n" +
                "  \"emp_lastname\": \"Vazquez\",\n" +
                "  \"emp_middle_name\": \"A\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"1997-09-03\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Automation Engineer\"\n" +
                "}");

        Response response = preparedRequest.when().put("/updateEmployee.php");

        response.prettyPrint();
    }

}
