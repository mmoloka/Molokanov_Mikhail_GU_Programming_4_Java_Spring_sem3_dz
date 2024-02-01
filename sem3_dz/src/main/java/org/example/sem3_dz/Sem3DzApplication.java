package org.example.sem3_dz;

import org.example.sem3_dz.model.User;
import org.example.sem3_dz.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sem3DzApplication {

    public static void main(String[] args) {

        UserRepository userRepository = SpringApplication.run(Sem3DzApplication.class, args).getBean(UserRepository.class);

        User user = new User();
        user.setId(1L);
        user.setLogin("login");
        user.setPassword("pass");
        userRepository.save(user);
    }

}
