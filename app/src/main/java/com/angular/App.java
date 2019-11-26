package com.angulartry.lexter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import java.util.stream.Stream;
/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    // @Bean
    // CommandLineRunner initialize(UserRepository userRepository) {
    //     return args -> {
    //         Stream.of("John", "Robert", "Nataly", "Helen", "Mary").forEach(name -> {
    //             User user = new User(name);
    //             user.setEmail(name+"@email.com");
    //             userRepository.save(user);
    //         });
    //         userRepository.findAll().forEach(System.out::println);
    //     };
    // }
}
