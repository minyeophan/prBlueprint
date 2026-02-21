package com.blueprint.backend.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.blueprint.backend.entity.User;
import com.blueprint.backend.repository.UserRepository;
import com.blueprint.backend.config.JwtUtil;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil; 
    
    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    
    public User signup(String email,String name, String password){
        if(userRepository.findByEmail(email).isPresent()){
            throw new RuntimeException("이미 가입된 이메일입니다");
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);

    }

    public String login(String email,String password){
        
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다"));
        
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("비밀번호가 틀립니다");
        }
        return jwtUtil.generateToken(email);
    }

}
