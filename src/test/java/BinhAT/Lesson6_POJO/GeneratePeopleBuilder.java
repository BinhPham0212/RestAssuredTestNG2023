package BinhAT.Lesson6_POJO;

import BinhAT.model.PeopleData_Lombok;
import BinhAT.model.data_builder.PeopleData;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.io.*;
import java.util.List;

public class GeneratePeopleBuilder {

    @Test
    public static void testGeneratePeople() {
        List<PeopleData_Lombok.Person> fakePeople = PeopleData.generateFakePeople(2);
        //Truyền dữ liệu vào class PeopleData_Lombok
        PeopleData_Lombok peopleDataLombok = new PeopleData_Lombok(1,fakePeople);

        try(FileWriter fileWriter = new FileWriter("src/test/resources/testdata/GenerateLombok.json")) {
            new Gson().toJson(peopleDataLombok, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File JSON updated successfully." + "\n" +peopleDataLombok );
        //In ra nội dung Json file vừa update

    }

    @Test
    public static void testBuilderPatternWithList() {
        PeopleData.peopleDataBuilder();    //Gọi lại hàm Generated builder

        try(FileWriter fileWriter = new FileWriter("src/test/resources/testdata/GenerateLombok.json")) {
            new Gson().toJson(PeopleData.peopleDataBuilder(), fileWriter);
            //Viết data được return builder.build() vào file Json
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File JSON updated successfully." + "\n" +PeopleData.peopleDataBuilder() );
        //In ra nội dung Json file vừa update
    }


}