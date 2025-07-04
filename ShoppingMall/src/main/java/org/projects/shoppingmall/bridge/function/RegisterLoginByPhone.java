package org.projects.shoppingmall.bridge.function;

import java.util.Date;
import javax.annotation.PostConstruct;
import org.projects.shoppingmall.bridge.abst.factory.RegisterLoginComponentFactory;
import org.projects.shoppingmall.pojo.UserInfo;
import org.projects.shoppingmall.pojo.dto.LoginRequest;
import org.projects.shoppingmall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterLoginByPhone implements RegisterLoginFuncInterface {
  private static final String USER_NAME_PREFIX = "user_";

  @Autowired
  private UserRepository userRepository;

  @PostConstruct
  private void initFuncMap() {
    RegisterLoginComponentFactory.funcMap.put("Phone", this);
  }

  @Override
  public String login(LoginRequest loginRequest) {
    UserInfo userInfo = userRepository.findByPhoneAndPassword(loginRequest.getPhone(), loginRequest.getPassword());
    return validUserLogin(userInfo);
  }

  @Override
  public String validUserLogin(UserInfo userInfo) {
    if (userInfo == null) {
      return "phone / password error";
    }
    return "login success";
  }

  @Override
  public String register(UserInfo userInfo) {
    if (checkUserExists(userInfo)) {
      return "user already exists";
    }
    userInfo.setUserName(USER_NAME_PREFIX + userInfo.getPhone());
    userInfo.setCreateDate(new Date());
    userRepository.save(userInfo);
    return "register success";
  }

  @Override
  public boolean checkUserExists(UserInfo userInfo) {
    UserInfo existUser = userRepository.findByPhone(userInfo.getPhone());
    return existUser != null;
  }
}
