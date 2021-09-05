package com.example.demo.datainit;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RequiredArgsConstructor
@Component
@Profile("dev")
public class UserInit {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @PostConstruct
    public void createTestUsers() {
        if (!userRepository.findByUsername("admin").isPresent()) {
            User admin = new User()
                    .setUsername("admin")
                    .setPassword(bCryptPasswordEncoder.encode("123"))
                    .setRoles(Arrays.asList(Role.ADMIN))
                    .setEmail("admin@gmail.com");
            userRepository.save(admin);
        }

        if (!userRepository.findByUsername("user").isPresent()) {
            User user = new User()
                    .setUsername("user")
                    .setPassword(bCryptPasswordEncoder.encode("123"))
                    .setRoles(Arrays.asList(Role.USER))
                    .setEmail("user@gmail.com");
            userRepository.save(user);
        }
    }
}
