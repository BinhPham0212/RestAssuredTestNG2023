package BinhAT.Lesson11_ReadJsonFile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UpdatePropertyInArrray {
    @Test
    public void testUpdateValueInJson03_ArrayObject() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile03.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to JsonArray
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);

            System.out.println("Original JSON: " + jsonArray);

            // Cập nhật giá trị của trường "lastname" trong phần tử đầu tiên
            if (jsonArray.size() > 0) {
                //Lấy vị trí object thứ nhất
                JsonObject firstObject = jsonArray.get(2).getAsJsonObject();
                firstObject.addProperty("lastname", "NewLastName");
            }

            System.out.println("Modified JSON: " + jsonArray);

            //Store new Json data to old file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile03.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            //Lưu đối tượng jsonArray chứ không phải jsonObject
            outputStream.write(gson.toJson(jsonArray).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateValueInJson03_ArrayObjectUseForAllIndex() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile03.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to JsonArray
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);

            System.out.println("Original JSON: " + jsonArray);

            // Cập nhật giá trị của trường "lastname" trong phần tử đầu tiên
            if (jsonArray.size() > 0) {
                //dùng vòng lặp for update tất cả các field "lastname" trong Array
                for(int i=0; i<jsonArray.size(); i++) {
                    JsonObject objectArray = jsonArray.get(i).getAsJsonObject();
                    objectArray.addProperty("lastname", "Lastname");
                }

            }

            System.out.println("Modified JSON: " + jsonArray);

            //Store new Json data to old file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile03.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            //Lưu đối tượng jsonArray chứ không phải jsonObject
            outputStream.write(gson.toJson(jsonArray).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    public void testUpdateValueInJson03_ArrayObjectFindFieldName() {
//
//        Reader reader;
//        String filePath = "src/test/resources/testdata/TestJsonFile03.json";
//
//        try {
//            reader = Files.newBufferedReader(Paths.get(filePath));
//
//            Gson gson = new Gson();
//            //Convert Json file to JsonArray
//            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
//
//            System.out.println("Original JSON: " + jsonArray);
//
//            // Cập nhật giá trị của trường "lastname" trong phần tử đầu tiên
//            if (jsonArray.size() > 0) {
//                //dùng vòng lặp for update tất cả các field "lastname" trong Array
//                JsonObject firstObject1 = jsonArray.get(jsonArray.size());
//
//                    firstObject1.addProperty("lastname", "NewLastName1");
//
//
//            }
//
//            System.out.println("Modified JSON: " + jsonArray);
//
//            //Store new Json data to old file
//            File jsonFile = new File("src/test/resources/testdata/TestJsonFile03.json");
//            OutputStream outputStream = new FileOutputStream(jsonFile);
//            //Lưu đối tượng jsonArray chứ không phải jsonObject
//            outputStream.write(gson.toJson(jsonArray).getBytes());
//            outputStream.flush();
//
//            //Close reader
//            reader.close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
