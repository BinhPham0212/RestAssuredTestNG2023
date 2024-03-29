package BinhAT.Lesson11_ReadJsonFile;

import BinhAT.helpers.JsonHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DemoEditJsonFile {
    @Test
    public void testUpdateValueInJson() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty("additionalneeds", "Update New Value");

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFileEdited.json");
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
    public void testUpdateValueToNewFile() {
        JsonHelper.updateValueToNewJsonFile("TestJsonFile01.json","additionalneeds",
                "UpdateToNewFile","TestJsonFileEdited.json");
    }

    @Test
    public void TC_testUpdateConstructor() {
        String testArrayObject= "src/test/resources/testdata/TestJsonArrayObject.json";
        String testArrayData01= "src/test/resources/testdata/TestJsonArrayObject02.json";
        JsonHelper.updateJsonValue(testArrayObject, "people.1.address.city.cityname", "Royalcity");
    }

    @Test
    public void TC01_testUpdateConstructor() {
        String testArrayObject= "src/test/resources/testdata/TestArrayData.json";
        JsonHelper.updateJsonValue(testArrayObject, "firstname.1", "Bình");
    }


}


