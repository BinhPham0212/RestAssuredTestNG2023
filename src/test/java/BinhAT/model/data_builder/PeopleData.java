package BinhAT.model.data_builder;

import BinhAT.model.PeopleData_Lombok;
import lombok.Builder;
import lombok.Data;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class PeopleData {

    //Build data for Class people
    public static List<PeopleData_Lombok.People> generateFakePeople(int numberOfPeople) {

        List<PeopleData_Lombok.People> peopleList = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < numberOfPeople; i++) {
            PeopleData_Lombok.People person = PeopleData_Lombok.People.builder()
                    .name(faker.name().fullName())
                    .age(faker.number().numberBetween(18, 70))
                    .address(PeopleData_Lombok.People.Address.builder()
                            .city(faker.address().city())
                            .country(faker.address().country())
                            .build())
                    .build();
            peopleList.add(person);
        }
        return peopleList;
    }


    public static PeopleData_Lombok peopleDataBuilder() {
//        List<PeopleData_Lombok.Person> fakePeople = PeopleData.generateFakePeople(2);
        //Truyền dữ liệu vào class PeopleData_Lombok

        PeopleData_Lombok.PeopleData_LombokBuilder builder = PeopleData_Lombok.builder();
        builder.id(1)
                .people(generateFakePeople(2))
                .build();

        return builder.build();
    }

    public static PeopleData_Lombok peopleDataBuilderNested() {
        //Truyền dữ liệu vào class PeopleData_Lombok

        PeopleData_Lombok.PeopleData_LombokBuilder builder = PeopleData_Lombok.builder();
        Faker faker = new Faker();
        builder.id(1)
                .people(List.of(PeopleData_Lombok.People.builder()
                        .name(faker.name().fullName())
                        .age(faker.number().numberBetween(18, 70))
                        .address(PeopleData_Lombok.People.Address.builder()
                                .city(faker.address().city())
                                .country(faker.address().country())
                                .build())
                        .build()))
                .build();

        return builder.build();
    }

}
