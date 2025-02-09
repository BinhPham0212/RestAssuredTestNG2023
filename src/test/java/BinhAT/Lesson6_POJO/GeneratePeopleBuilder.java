package BinhAT.Lesson6_POJO;

import BinhAT.model.PeopleData_Lombok;
import BinhAT.model.data_builder.PeopleData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.testng.annotations.Test;

import java.io.*;
import java.util.List;

public class GeneratePeopleBuilder {

    @Test
    public static void testGeneratePeople() {
        List<PeopleData_Lombok.People> fakePeople = PeopleData.generateFakePeople(10);
        //Truyền dữ liệu vào class PeopleData_Lombok, cần phải có @AllArgsConstructor
        PeopleData_Lombok peopleDataLombok = new PeopleData_Lombok(45, fakePeople);

        try (FileWriter fileWriter = new FileWriter("src/test/resources/testdata/GenerateLombok.json")) {
            new Gson().toJson(peopleDataLombok, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File JSON updated successfully." + "\n" + peopleDataLombok);
        //In ra nội dung Json file vừa update

    }

    @Test
    public static void testBuilderPatternWithList() {
        PeopleData_Lombok peopleDataLombok = PeopleData.peopleDataBuilder();    //Gọi lại hàm peopleDataBuilder, hàm trả về class PeopleData_Lombok

        try (FileWriter fileWriter = new FileWriter("src/test/resources/testdata/GenerateLombok.json")) {
            new Gson().toJson(peopleDataLombok, fileWriter);
            //Viết data được return builder.build() vào file Json
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File JSON updated successfully." + "\n" + PeopleData.peopleDataBuilder());
        //In ra nội dung Json file vừa update
    }

    @Test
    public static void testBuilderPatternBuilderNested() throws FileNotFoundException {
        PeopleData_Lombok peopleBuilderNested = PeopleData.peopleDataBuilderNested();

        try (FileWriter fileWriter = new FileWriter("src/test/resources/testdata/GenerateLombok.json")) {
            new Gson().toJson(peopleBuilderNested, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File JSON updated successfully." + "\n" + PeopleData.peopleDataBuilder());
        //In ra nội dung Json file vừa update
    }


}