package pl.bsobieski.crudlibrary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bsobieski.crudlibrary.entities.User;
import pl.bsobieski.crudlibrary.services.UserService;
//TODO login
//TODO Registration
//TODO JWT

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public Iterable<User> getUsers(){
        return userService.getAllUsers();
    }
}
