package org.projects.shoppingmall.service;

import java.util.Date;
import org.projects.shoppingmall.pojo.UserInfo;
import org.projects.shoppingmall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  private static final String USER_NAME_PREFIX = "user_";

  public String login(String account, String password) {
    UserInfo userInfo = userRepository.findByUserNameAndPassword(account, password);
    if (userInfo == null) {
      return "account / password error";
    }
    return "login success";
  }

  public String register(UserInfo userInfo) {
    if (checkUserExists(userInfo)) {
      return "user already exists";
    }
    userInfo.setCreateDate(new Date());
    userRepository.save(userInfo);
    return "register success";
  }

  private boolean checkUserExists(UserInfo userInfo) {
    UserInfo existUser = null;
    if (userInfo.getPhone() != null) {
      existUser = userRepository.findByPhone(userInfo.getPhone());
      return existUser != null;
    }
    existUser = userRepository.findByUserName(userInfo.getUserName());
    return existUser != null;
  }

  public String phoneLogin(String phone, String password) {
    UserInfo userInfo = userRepository.findByPhoneAndPassword(phone, password);
    if (userInfo == null) {
      return "phone / password error";
    }
    return "login success";
  }

  public String phoneRegister(UserInfo userInfo) {
    if (checkUserExists(userInfo)) {
      return "user already exists";
    }
    userInfo.setUserName(USER_NAME_PREFIX + userInfo.getPhone());
    userInfo.setCreateDate(new Date());
    userRepository.save(userInfo);
    return "register success";
  }
}
