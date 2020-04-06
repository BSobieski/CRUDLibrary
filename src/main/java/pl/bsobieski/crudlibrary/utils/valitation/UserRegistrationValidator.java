package pl.bsobieski.crudlibrary.utils.valitation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.bsobieski.crudlibrary.dto.UserRegistrationDto;
import pl.bsobieski.crudlibrary.entities.User;
import pl.bsobieski.crudlibrary.services.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 1000 - something is missing
 * 1001 - username already in database
 * 1002 - username need to be 6-32 long
 * 1003 - password need to be 8-32 long
 * 1004 - password not following the instructions
 * 1005 - password and confirm password are not the same
 */

@Component
public class UserRegistrationValidator implements Validator {

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%!'\"%&*()_=]).*)";
    private Integer errorCode;
    private Pattern pattern;
    private Matcher matcher;
    private final UserService userService;

    @Autowired
    public UserRegistrationValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        pattern = Pattern.compile(PASSWORD_PATTERN);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "password confirm cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "first name cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "last name cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "phone number cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "email address cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "date of birth cannot be empty");

        if (errors.hasErrors()) {
            errorCode = 1000;
            return;
        }

        UserRegistrationDto userRegistrationDto = (UserRegistrationDto) o;

        if (userService.getUserByUsername(userRegistrationDto.getUsername()).isPresent()) {
            errors.rejectValue("username", "This username is already in data base");
            errorCode = 1001;
        }

        if (userRegistrationDto.getUsername().length() < 6 || userRegistrationDto.getUsername().length() > 32) {
            errors.rejectValue("username", "username need to be 6-32 long");
            errorCode = 1002;
        }

        if (userRegistrationDto.getPassword().length() < 8 || userRegistrationDto.getPassword().length() > 32) {
            errors.rejectValue("password", "password need to be 8-32 long");
            errorCode = 1003;
        }

        if (!validatePassword(userRegistrationDto.getPassword())) {
            errors.rejectValue("password", "password not following the instructions");
            errorCode = 1004;
        }

        if (!userRegistrationDto.getPasswordConfirm().equals(userRegistrationDto.getPassword())) {
            errors.rejectValue("passwordConfirm", "password and confirm password are not the same");
            errorCode = 1005;
        }
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    private boolean validatePassword(String password) {
        return pattern.matcher(password).matches();
    }
}
