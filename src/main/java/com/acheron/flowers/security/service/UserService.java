package com.acheron.flowers.security.service;

import com.acheron.flowers.security.dto.RegistrationRequest;
import com.acheron.flowers.security.entity.Role;
import com.acheron.flowers.security.entity.User;
import com.acheron.flowers.security.exception.UserAlreadyExistsException;
import com.acheron.flowers.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow();
    }

    public User save(RegistrationRequest request) throws UserAlreadyExistsException {
        if(userAlreadyExists(request.getEmail())){
            String password = passwordEncoder.encode(request.getPassword());
            return userRepository.save(new User(null, request.getFirstName(), request.getLastName(), request.getEmail(), password,request.getPhoneNumber(), Role.USER));
        }else {
            throw new UserAlreadyExistsException();
        }

    }

    private boolean userAlreadyExists(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
