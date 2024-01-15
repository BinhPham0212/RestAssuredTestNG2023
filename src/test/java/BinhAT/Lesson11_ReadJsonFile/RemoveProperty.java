package BinhAT.Lesson11_ReadJsonFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RemoveProperty {
    @Test
    public void testRemovePropertyInJson() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile02.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            jsonObject.remove("age");

            //Xác định vị trí của property cần xoá
            JsonObject positionObject = jsonObject
                    .get("department").getAsJsonObject()
                    .get("position").getAsJsonObject();

            //Xoá key "years" trong cấu trúc propert
            positionObject.remove("years");

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile02Edited.json");
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
