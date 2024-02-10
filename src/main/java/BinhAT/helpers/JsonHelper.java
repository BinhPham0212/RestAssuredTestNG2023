package BinhAT.helpers;

import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHelper {
    public static String readValueJsonObject(String keyName, String filePath) {

        Reader reader;
        //String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        String valueName;
        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            //Update value if exist key
            valueName = jsonObject.get(keyName).getAsString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return valueName;
    }
    public static void updateValueJsonFile(String filePath, String keyName, int value) {

        Reader reader;
        //String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty(keyName, value);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateValueJsonFile(String filePath, String keyName, String value) {

        Reader reader;
        //String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty(keyName, value);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateValueJsonFile(String filePath, String keyName, Boolean value) {

        Reader reader;
        //String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty(keyName, value);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateValueToNewJsonFile(String filePath, String keyName, String value,String newFilePath) {

        Reader reader;
        //String filePath = "src/test/resources/testdata/TestJsonFile01.json";
        try {

            reader = Files.newBufferedReader(Paths.get(SystemHelper.getCurrentDirDataJson() + filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON of " +filePath+ ":" + jsonObject);

            //Update value if exist key
            jsonObject.addProperty(keyName, value);

            System.out.println("Modified JSON to " +newFilePath+ ":" + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(SystemHelper.getCurrentDirDataJson() + newFilePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * UpdateJsonFile
     * key.split("\\."): Một mảng các chuỗi được tách ra từ key dựa trên dấu chấm (.) làm dấu phân cách.
     * Ví dụ, nếu key là "address.city", thì mảng sẽ là ["address", "city"]. Mỗi phần tử của mảng đại diện cho một cấp bậc trong cây JSON.
     *
     * 0: Chỉ số hiện tại của mảng key. Trong trường hợp này, ta bắt đầu từ đầu mảng, nên chỉ số là 0.
     */
    public static void updateJsonValue(String filePath, String key, Object newValue) {
        try {
            // Đọc và in nội dung từ file JSON
            JsonElement jsonElement = JsonParser.parseReader(new FileReader(filePath));
            System.out.println("Original JSON of " +filePath+ ":\n" + jsonElement);

            // Cập nhật giá trị cho key
            updateValue(jsonElement.getAsJsonObject(), key.split("\\."), 0, newValue);
            // Ghi lại nội dung đã cập nhật vào file JSON
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                new Gson().toJson(jsonElement, fileWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("File JSON updated successfully.");
            //In ra nội dung Json file vừa update
            System.out.println("Modified JSON: " + jsonElement);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateJsonValueToNewFile(String filePath, String key, Object newValue, String newFilePath) {
        try {
            // Đọc và in nội dung từ file JSON
            JsonElement jsonElement = JsonParser.parseReader(new FileReader(filePath));
            System.out.println("Original JSON of " +filePath+ ":\n" + jsonElement);

            // Cập nhật giá trị cho key
            updateValue(jsonElement.getAsJsonObject(), key.split("\\."), 0, newValue);

            // Ghi lại nội dung đã cập nhật vào file JSON
            try (FileWriter fileWriter = new FileWriter(newFilePath)) {
                new Gson().toJson(jsonElement, fileWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("File JSON updated successfully.");
            //In ra nội dung Json file vừa update
            System.out.println("Modified JSON to " +newFilePath+ ":\n"  + jsonElement);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateValue(JsonObject jsonObject, String[] keys, int currentIndex, Object newValue) {
        if (currentIndex == keys.length - 1) {
            // Đến phần tử cần cập nhật, gán giá trị mới
            if (newValue instanceof String) {
                jsonObject.add(keys[currentIndex], new JsonPrimitive((String) newValue));
            } else if (newValue instanceof Integer) {
                jsonObject.add(keys[currentIndex], new JsonPrimitive((Integer) newValue));
            } else if (newValue instanceof Boolean) {
                jsonObject.add(keys[currentIndex], new JsonPrimitive((Boolean) newValue));
            }
        } else {
            // Chưa đến phần tử cuối cùng, tiếp tục đệ quy
            String currentKey = keys[currentIndex];
            if (jsonObject.has(currentKey)) {
                JsonElement jsonElement = jsonObject.get(currentKey);
                if (jsonElement.isJsonObject()) {
                    updateValue(jsonElement.getAsJsonObject(), keys, currentIndex + 1, newValue);
                } else if (jsonElement.isJsonArray()) {
                    updateValueInArray(jsonElement.getAsJsonArray(), keys, currentIndex + 1, newValue);
                }
            }
        }
    }

    private static void updateValueInArray(JsonArray jsonArray, String[] keys, int currentIndex, Object newValue) {
        int arrayIndex = Integer.parseInt(keys[currentIndex]);
        if (arrayIndex >= 0 && arrayIndex < jsonArray.size()) {
            JsonElement jsonElement = jsonArray.get(arrayIndex);
            if (jsonElement.isJsonObject()) {
                updateValue(jsonElement.getAsJsonObject(), keys, currentIndex + 1, newValue);
            } else if (jsonElement.isJsonArray()) {
                updateValueInArray(jsonElement.getAsJsonArray(), keys, currentIndex + 1, newValue);
            }
        }
    }
    /**
     *
     */





}
