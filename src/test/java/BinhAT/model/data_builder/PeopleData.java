package BinhAT.model.data_builder;

import BinhAT.model.PeopleData_Lombok;
import lombok.Builder;
import lombok.Data;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class PeopleData {
    public static List<PeopleData_Lombok.Person> generateFakePeople(int numberOfPeople) {

        List<PeopleData_Lombok.Person> peopleList = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < numberOfPeople; i++) {
            PeopleData_Lombok.Person person = PeopleData_Lombok.Person.builder()
                    .name(faker.name().fullName())
                    .age(faker.number().numberBetween(18, 70))
                    .address(PeopleData_Lombok.Person.Address.builder()
                            .city(faker.address().city())
                            .country(faker.address().country())
                            .build())
                    .build();
            peopleList.add(person);
        }
        return peopleList;
    }

    public static PeopleData_Lombok peopleDataBuilder() {
        List<PeopleData_Lombok.Person> fakePeople = PeopleData.generateFakePeople(2);
        //Truyền dữ liệu vào class PeopleData_Lombok

        PeopleData_Lombok.PeopleData_LombokBuilder builder = PeopleData_Lombok.builder();
        builder.id(1)
                .people(fakePeople)
                .build();

        return builder.build();
    }


}
