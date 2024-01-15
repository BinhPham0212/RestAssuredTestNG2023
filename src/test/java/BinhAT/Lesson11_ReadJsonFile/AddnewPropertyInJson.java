package BinhAT.Lesson11_ReadJsonFile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class AddnewPropertyInJson {
    @Test
    public void testAddNewPropertyInJson() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile01Edited.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //--Create array value
            JsonArray objectArray = new JsonArray();
            objectArray.add("Support");
            objectArray.add("ERP");
            objectArray.add("2024");
            //Add array value to key "department"
            jsonObject.add("department", objectArray);

            //Add simple key:value
            jsonObject.addProperty("key1", "Value for Key1");

            //Add key:{object}
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("name", "Anh Tester");
            objectMap.put("age", 27);
            JsonElement jsonElement = gson.toJsonTree(objectMap);
            jsonObject.add("student", jsonElement);

//----------Thêm mới 2 level field con
            Map<String, Object> departmentMap = new HashMap<>();              // Tạo một đối tượng Map cho phần "department", property parent
            departmentMap.put("name", "HR");
            departmentMap.put("class", "world");
            departmentMap.put("Full name", "HR Leader");

            Map<String, Object> positionMap = new HashMap<>();                // Tạo một đối tượng Map cho phần "position"
            positionMap.put("role", "lead");
            positionMap.put("years", "3");

            departmentMap.put("position", positionMap);   // Gắn "position" vào "department"
            JsonElement jsonElement1 = gson.toJsonTree(departmentMap);              //Gson ghi file dạng tree của department1 MAP
            jsonObject.add("department1", jsonElement1);                    //Thêm ten field ngoài cùng "department1"
//----------

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to old file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile01Edited.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddNewPropertySubFieldInArray() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonArray.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            Map<String, Object> departmentMap = new HashMap<>();              // Tạo một đối tượng Map cho phần "department", property parent
            departmentMap.put("name", "HR");
            departmentMap.put("class", "world");
            departmentMap.put("Full name", "HR Leader");

            Map<String, Object> positionMap = new HashMap<>();                // Tạo một đối tượng Map cho phần "position"
            positionMap.put("role", "lead");
            positionMap.put("years", "3");

            departmentMap.put("position", positionMap);   // Gắn "position" vào "department"
            JsonElement jsonElement1 = gson.toJsonTree(departmentMap);              //Gson ghi file dạng tree của department1 MAP
            jsonObject.add("department1", jsonElement1);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to old file
            File jsonFile = new File("src/test/resources/testdata/TestJsonArray.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();
            //Close reader
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
