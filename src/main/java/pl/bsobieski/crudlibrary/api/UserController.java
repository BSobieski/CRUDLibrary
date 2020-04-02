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
//TODO Registration

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/user/all")
    public Iterable<User> getUsers(){
        return userService.getAllUsers();
    }

    //TODO Validation
    @PostMapping("/registration")
    //@ModelAttribute("userToRegistration")
    public ResponseEntity registration(@RequestBody UserRegistrationDto userToRegistration, BindingResult bindingResult){
        /*
        *
        * Register Validation
        * */
        userService.save(userToRegistration);
        return new ResponseEntity(userToRegistration, HttpStatus.OK);
    }

    @PostMapping("/login")
    public String logIn(@RequestBody UserLoginDto userLoginDto){
        UserTokenDto userTokenDto = userService.checkUser(userLoginDto.getUsername(), userLoginDto.getPassword());
        return userService.createToken(userTokenDto);
    }
}
