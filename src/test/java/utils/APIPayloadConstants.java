package utils;

import org.json.JSONObject;

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

    public static String createEmployeeBody(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Estela");
        obj.put("emp_lastname", "Vazquez");
        obj.put("emp_middle_name", "A");
        obj.put("emp_gender", "F");
        obj.put("emp_birthday", "1997-09-03");
        obj.put("emp_status", "Employee");
        obj.put("emp_job_title", "Automation Engineer");

        return obj.toString();
    }

    public static String createEmployeeBodyMoreDynamic(String firstName, String lastName, String middleName, String gender,
                                                       String birthday, String employeeStatus, String jobTitle){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", firstName);
        obj.put("emp_lastname", lastName);
        obj.put("emp_middle_name", middleName);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", birthday);
        obj.put("emp_status", employeeStatus);
        obj.put("emp_job_title", jobTitle);

        return obj.toString();
    }
}
