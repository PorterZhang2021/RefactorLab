package org.projects.shoppingmall.bridge.function;

import java.util.Date;
import org.projects.shoppingmall.pojo.UserInfo;
import org.projects.shoppingmall.pojo.dto.LoginRequest;
import org.projects.shoppingmall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterLoginByDefault implements RegisterLoginFuncInterface {
  @Autowired
  private UserRepository userRepository;

  @Override
  public String login(LoginRequest loginRequest) {
    UserInfo userInfo =
        userRepository.findByUserNameAndPassword(loginRequest.getUserName(), loginRequest.getPassword());
    if (userInfo == null) {
      return "account / password error";
    }
    return "login success";
  }

  @Override
  public String register(UserInfo userInfo) {
    if (checkUserExists(userInfo)) {
      return "user already exists";
    }
    userInfo.setCreateDate(new Date());
    userRepository.save(userInfo);
    return "register success";
  }

  @Override
  public boolean checkUserExists(UserInfo userInfo) {
    UserInfo existUser = userRepository.findByUserName(userInfo.getUserName());
    return existUser != null;
  }
}
