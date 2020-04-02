package pl.bsobieski.crudlibrary.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Beans {

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
