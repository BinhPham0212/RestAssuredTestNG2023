package BinhAT.Lesson11_ReadJsonFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UpdateSubClass {
    @Test
    public void testUpdateValueInJson02() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            // Cập nhật giá trị của trường "checkout"
            jsonObject.getAsJsonObject("bookingdates").addProperty("checkout", "2024-04-10");
            jsonObject.getAsJsonObject("bookingdates").addProperty("status","true");

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to old file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile01.json");
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
