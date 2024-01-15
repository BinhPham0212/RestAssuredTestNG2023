package BinhAT.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserPOJO_Lombok {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;


    public RegisterUserPOJO_Lombok(String username, String firstName, String lastName) {

    }



}
