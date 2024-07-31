package com.example.db.controller;

import com.example.db.dto.AuthRequest;
import com.example.db.dto.UserRequest;
import com.example.db.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;


    @PostMapping("/reg")
    public ResponseEntity<?> registration(@RequestBody UserRequest userRequest) {
        return userService.registration(userRequest.getName(), userRequest.getSurname(), userRequest.getLogin(), userRequest.getPassword(),userRequest.getRole(),userRequest.getCode());
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authorization(@RequestBody AuthRequest authRequest) {
        return userService.authorization(authRequest.getLogin(), authRequest.getPassword());
    }

}