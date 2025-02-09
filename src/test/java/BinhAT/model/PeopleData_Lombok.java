package BinhAT.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PeopleData_Lombok {
    private int id;
    private List<People> people;

    @Data
    @Builder
    public static class People {
        private String name;
        private int age;
        private Address address;

        @Data
        @Builder
        public static class Address {
            private String city;
            private String country;
        }

    }

}
