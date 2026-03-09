package com.blueprint.backend.controller;
import com.blueprint.backend.entity.User;
import com.blueprint.backend.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;


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
    public ResponseEntity<String> login(@RequestBody User user,HttpServletResponse response) {
        String token = authService.login(user.getEmail(), user.getPassword());
        ResponseCookie cookie = ResponseCookie.from("token",token)
            .httpOnly(true)
            .path("/")
            .maxAge(86400)
            .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok("로그인 성공");
    }

    @GetMapping("/me")
    public ResponseEntity<String> me(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if(email == null || email.equals("anonymousUser")){
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(email);
    }

}
