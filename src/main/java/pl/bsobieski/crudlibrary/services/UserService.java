package pl.bsobieski.crudlibrary.services;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.bsobieski.crudlibrary.dto.UserRegistrationDto;
import pl.bsobieski.crudlibrary.dto.UserTokenDto;
import pl.bsobieski.crudlibrary.entities.User;
import pl.bsobieski.crudlibrary.repositories.UserRepository;

import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    public void save(UserRegistrationDto userRegistrationDto) {
        User user = convertToUser(userRegistrationDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }

    public Iterable<User> saveAll(Iterable<User> userIterable) {
        return userRepository.saveAll(userIterable);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    private User convertToUser(UserRegistrationDto userRegistrationDto) {
        return modelMapper.map(userRegistrationDto, User.class);
    }

    private UserTokenDto convertToUserTokenDto(User user){
        return modelMapper.map(user, UserTokenDto.class);
    }

    public UserTokenDto checkUser(String login, String password) {
        User user = userRepository.findByUsername(login).orElse(null);
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        if (user != null && bCryptPasswordEncoder.matches(password,user.getPassword())) {
            return convertToUserTokenDto(user);
        }
        return null;
    }

    //TODO change to bcrypt secret key
    //TODO transport secret key to database
    //TODO add IssuedTo
    public String createToken(UserTokenDto userTokenDto) {
        if (userTokenDto != null) {
            Header header = Jwts.header();
            header.setType("JWT");
            return Jwts.builder().setHeader((Map<String, Object>)header)
                    .setSubject(userTokenDto.getUsername())
                    .claim("firstName", userTokenDto.getFirstName())
                    .claim("lastName", userTokenDto.getLastName())
                    .claim("emailAdress", userTokenDto.getEmailAddress())
                    .claim("role", userTokenDto.getRole())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString("cLSMQk$h~cYik?k".getBytes()))
                    .compact();
        }
        return null;
    }
}
