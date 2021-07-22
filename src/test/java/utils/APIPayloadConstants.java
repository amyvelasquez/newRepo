package utils;

public class APIPayloadConstants {

    public static String createEmployeePayload (){
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"Estela\",\n" +
                "  \"emp_lastname\": \"Vazquez\",\n" +
                "  \"emp_middle_name\": \"A\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"1997-09-03\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Automation Engineer\"\n" +
                "}";
        return createEmployeePayload;
    }
}
