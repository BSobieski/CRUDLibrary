package pl.bsobieski.crudlibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bsobieski.crudlibrary.entities.User;
import pl.bsobieski.crudlibrary.repositories.UserRepository;

import javax.naming.Context;
import java.util.Base64;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user){
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        user.setRole("USER");
        userRepository.save(user);
    }

    public Iterable<User> saveAll(Iterable<User> userIterable){
        return userRepository.saveAll(userIterable);
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
}
