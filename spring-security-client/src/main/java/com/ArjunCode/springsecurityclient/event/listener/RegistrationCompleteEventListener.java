package com.ArjunCode.springsecurityclient.event.listener;

import com.ArjunCode.springsecurityclient.entity.User;
import com.ArjunCode.springsecurityclient.event.RegistrationCompleteEvent;
import com.ArjunCode.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    //NEED USER SERVICE
    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create the verification token for the User with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        userService.saveVerificationTokenForUser(token,user);

        //Send mail to User
        String url = event.getApplicationUrl() + "verifyRegistration?token="+token;

        //sendVerificationEmail() need to implemnt this method, curently we are mocking in console.
        log.info("Click the link to verify your account: {}",url);

    }
}
