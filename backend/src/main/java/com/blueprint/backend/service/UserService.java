package com.blueprint.backend.service;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.blueprint.backend.entity.User;
import com.blueprint.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserCacheService userCacheService;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws Exception {
        User cachedUser = userCacheService.getUser(id);
        if(cachedUser != null){
            return cachedUser;
        }
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            userCacheService.saveUser(id ,user);
        }
        return user;
        
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id); 
        userCacheService.deleteUser(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

}
