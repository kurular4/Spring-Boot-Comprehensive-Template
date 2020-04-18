package com.example.demo.datainit;

import com.example.demo.model.Document;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@Profile("dev")
public class UserInit {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void createUserWithRoleAdmin() {
        if (!userRepository.findByUsername("admin").isPresent()) {
            User admin = new User()
                    .setUsername("admin")
                    .setPassword(bCryptPasswordEncoder.encode("123"))
                    .setRoles(Arrays.asList(Role.ADMIN))
                    .setEmail("admin@gmail.com")
                    .setDocumentList(Arrays.asList(new Document()
                            .setName("document1")
                            .setAuthor("person1")
                            .setPageCount(144), new Document()
                            .setName("document2")
                            .setAuthor("person1")
                            .setPageCount(244)));
            userRepository.save(admin);
        }
    }

    @PostConstruct
    public void createUserWithRoleUser() {
        if (!userRepository.findByUsername("user").isPresent()) {
            User user = new User()
                    .setUsername("user")
                    .setPassword(bCryptPasswordEncoder.encode("123"))
                    .setRoles(Arrays.asList(Role.USER))
                    .setEmail("user@gmail.com")
                    .setDocumentList(Arrays.asList(new Document()
                            .setName("document1")
                            .setAuthor("person2")
                            .setPageCount(120), new Document()
                            .setName("document2")
                            .setAuthor("person2")
                            .setPageCount(122)));
            userRepository.save(user);
        }
    }
}
