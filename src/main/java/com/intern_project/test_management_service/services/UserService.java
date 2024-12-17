package com.intern_project.test_management_service.services;

import com.intern_project.test_management_service.models.User;
import com.intern_project.test_management_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public User addUser(User user) {

        return userRepository.save(user);
    }
}