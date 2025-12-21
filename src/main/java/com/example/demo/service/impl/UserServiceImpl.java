// package com.example.demo.service.impl;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class UserServiceImpl implements UserService {

//     private final UserRepository userRepository;

//     public UserServiceImpl(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @Override
//     public User registerUser(User user) {

//         if (userRepository.existsByEmail(user.getEmail())) {
//             throw new IllegalArgumentException("Email already in use");
//         }

      
//     }

//     @Override
//     public User findByEmail(String email) {
//         return userRepository.findByEmail(email)
//                 .orElseThrow(() ->
//                         new ResourceNotFoundException("User not found"));
//     }

//     @Override
//     public List<User> getAllUsers() {
//         return userRepository.findAll();
//     }
// }
