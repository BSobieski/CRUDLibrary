package pl.bsobieski.crudlibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bsobieski.crudlibrary.entities.User;
import pl.bsobieski.crudlibrary.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO encrypting password
    public void save(User user){
        //encrypting password
        user.setRoles("USER");
        user.setPermissions("");
        userRepository.save(user);
    }

    public Iterable<User> saveAll(Iterable<User> userIterable){
        return userRepository.saveAll(userIterable);
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
}
