package teksystem.casestudyDemo.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterFormBean {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String passwordConfirm;
}
