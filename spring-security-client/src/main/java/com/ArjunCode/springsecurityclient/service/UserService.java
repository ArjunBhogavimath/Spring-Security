package com.ArjunCode.springsecurityclient.service;

import com.ArjunCode.springsecurityclient.entity.User;
import com.ArjunCode.springsecurityclient.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);
}
