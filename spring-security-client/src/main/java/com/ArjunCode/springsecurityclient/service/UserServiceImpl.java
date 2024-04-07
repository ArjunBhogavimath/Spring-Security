package com.ArjunCode.springsecurityclient.service;

import com.ArjunCode.springsecurityclient.entity.User;
import com.ArjunCode.springsecurityclient.entity.VerificationToken;
import com.ArjunCode.springsecurityclient.model.UserModel;
import com.ArjunCode.springsecurityclient.repository.UserRepository;
import com.ArjunCode.springsecurityclient.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    //need userRepository as well so create object
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail()); //Setting user details by getting it from userModel
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken  verificationToken= new VerificationToken(user, token);

        verificationTokenRepository.save(verificationToken); //token is saved to db
    }
}
