package teksystem.casestudyDemo.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import teksystem.casestudyDemo.validation.EmailUnique;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    @EmailUnique(message = "Email is already taken")
    @NotBlank(message = "Email is required")
//    @Pattern(regexp = "^[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message="Email format invalid")
    @Email(message = "Not a Valid Email")
    private String email;
    @NotBlank(message = "First Name is required")
    private String firstName;
    @NotBlank(message = "Last Name is required")
    private String lastName;
    @Length(min = 8, max = 20, message = "Password must be between 8 to 20 character")
    @NotBlank(message = "Password is required")
    private String password;
    @Length(min = 8, max = 20, message = "Password must be between 8 to 20 character")
    @NotBlank(message = "Password Confirm is required")
    private String passwordConfirm;

    @AssertTrue(message = "Checkbox is required")
    private boolean checkbox;


}
