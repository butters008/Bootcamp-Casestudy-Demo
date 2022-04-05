package teksystem.casestudyDemo.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class RegisterFormBean {
    /*
    * This will be null when we are creating a new object, since we are AI in the DB
    * Also, this wll hold the value of anything we are pulling from the DB however,
    * this will be hidden on the page.
    * */
    private Integer id;

    /*
    * This annotation is from <artifactId>spring-boot-starter-validation</artifactId>, and this is where we are
    * starting to get into the logic of logging in.
    *
    * (I think!) We could possibly use this for error handling in creating a new user as well
    * */
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "First Name is required")
    private String firstName;
    @NotBlank(message = "Last Name is required")
    private String lastName;
    @Length(min=8, max=20, message="Password must be between 8 to 20 character")
    @NotBlank(message = "Password is required")
    private String password;
    @Length(min=8, max=20, message="Password must be between 8 to 20 character")
    @NotBlank(message = "Password Confirm is required")
    private String passwordConfirm;



}
