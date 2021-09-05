package com.example.demo.service;

import com.example.demo.dto.GenericResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.user.EmailExistsException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.exception.user.UsernameExistsException;
import com.example.demo.exception.user.UsernameNotFoundException;
import com.example.demo.model.Document;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GenericResponseService genericResponseService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ModelMapper modelMapper;

    public GenericResponse create(UserDTO userDTO) {
        Objects.requireNonNull(userDTO);

        if (usernameExists(userDTO.getUsername())) {
            throw new UsernameExistsException();
        } else if (emailExists(userDTO.getEmail())) {
            throw new EmailExistsException();
        }

        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()))
                .setRoles(Collections.singletonList(Role.USER))
                .setId(UUID.randomUUID().toString());

        if (userRepository.save(user) != null) {
            return genericResponseService.createResponseNoError(user);
        } else throw new RuntimeException();
    }

    public GenericResponse update(String id, UserDTO userDTO) {
        Objects.requireNonNull(userDTO);
        
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setUsername(userDTO.getUsername())
                .setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .setEmail(userDTO.getEmail());

        if (userRepository.save(user) != null) {
            return genericResponseService.createResponseNoError(user);
        } else throw new RuntimeException();
    }

    public GenericResponse remove(String id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return genericResponseService.createResponseNoError(true);
    }

    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User loadUserByUsername(String s) {
        return userRepository.findByUsername(s).orElseThrow(UsernameNotFoundException::new);
    }

    public GenericResponse findAllDocuments(User user) {
        Objects.requireNonNull(user);
        User userFetched = loadUserByUsername(user.getUsername());
        return genericResponseService.createResponseNoError(userFetched.getDocumentList());
    }

    public GenericResponse addDocument(User user, Document document) {
        User userFetched = loadUserByUsername(user.getUsername());
        List<Document> documentList = userFetched.getDocumentList();
        documentList.add(document);
        if (userRepository.save(userFetched) != null) {
            return genericResponseService.createResponseNoError(userFetched);
        } else throw new RuntimeException();
    }
}
