package pl.bsobieski.crudlibrary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bsobieski.crudlibrary.dto.UserLoginDto;
import pl.bsobieski.crudlibrary.dto.UserRegistrationDto;
import pl.bsobieski.crudlibrary.dto.UserTokenDto;
import pl.bsobieski.crudlibrary.entities.User;
import pl.bsobieski.crudlibrary.services.UserService;
import pl.bsobieski.crudlibrary.utils.Utils;
import pl.bsobieski.crudlibrary.utils.errors.ApiError;
import pl.bsobieski.crudlibrary.utils.valitation.UserRegistrationValidator;
//TODO Registration

@RestController
public class UserController {
    private UserService userService;
    private UserRegistrationValidator userRegistrationValidator;

    @Autowired
    public UserController(UserService userService, UserRegistrationValidator userRegistrationValidator) {
        this.userService = userService;
        this.userRegistrationValidator = userRegistrationValidator;
    }

    @GetMapping("/admin/user/all")
    public Iterable<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/registration")
    //@ModelAttribute("userToRegistration")
    public ResponseEntity registration(@RequestBody UserRegistrationDto userToRegistration, BindingResult bindingResult) {
        userRegistrationValidator.validate(userToRegistration, bindingResult);

        if (bindingResult.hasErrors()) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, userRegistrationValidator.getErrorCode(), Utils.getErrorMessage(bindingResult));
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }

        userService.save(userToRegistration);
        return new ResponseEntity(userToRegistration, HttpStatus.OK);
    }

    @PostMapping("/login")
    public String logIn(@RequestBody UserLoginDto userLoginDto) {
        UserTokenDto userTokenDto = userService.checkUser(userLoginDto.getUsername(), userLoginDto.getPassword());
        return userService.createToken(userTokenDto);
    }
}
