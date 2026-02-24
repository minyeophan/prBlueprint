package com.blueprint.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueprint.backend.entity.User;
import com.blueprint.backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
   private final AuthService authService;

   public AuthController(AuthService authService) {
    this.authService = authService;
   }

   @PostMapping("/signup")
   public User signup(@RequestBody User user) {
       return authService.signup(user.getEmail(), user.getName(), user.getPassword());
   }

   @PostMapping("/login")
   public String login(@RequestBody User user) {
       return authService.login(user.getEmail(), user.getPassword());
   }

}
