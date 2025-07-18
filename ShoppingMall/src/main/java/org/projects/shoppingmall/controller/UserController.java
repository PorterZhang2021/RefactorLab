package org.projects.shoppingmall.controller;

import org.projects.shoppingmall.pojo.UserInfo;
import org.projects.shoppingmall.pojo.dto.LoginRequest;
import org.projects.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public String login(@RequestBody LoginRequest loginRequest) {
    return userService.login(loginRequest);
  }

  @PostMapping("/register")
  public String register(@RequestBody UserInfo userInfo) {
    return userService.register(userInfo);
  }

}
