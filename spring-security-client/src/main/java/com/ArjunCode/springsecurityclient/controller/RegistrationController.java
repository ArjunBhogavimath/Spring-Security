package com.ArjunCode.springsecurityclient.controller;

import com.ArjunCode.springsecurityclient.entity.User;
import com.ArjunCode.springsecurityclient.event.RegistrationCompleteEvent;
import com.ArjunCode.springsecurityclient.model.UserModel;
import com.ArjunCode.springsecurityclient.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    //we need user service as well. and from that we call repository
    @Autowired
    private UserService userService;

    //create an event to seperate out the process with the current flow
    @Autowired
    private ApplicationEventPublisher publisher;


    @PostMapping("/regiter")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){ //created user model will be used here
        User user = userService.registerUser(userModel); //need to implement registeruser method in userService
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Success";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+ request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
