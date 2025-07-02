package org.projects.shoppingmall.service;

import org.apache.commons.lang3.StringUtils;
import org.projects.shoppingmall.bridge.abst.AbstractRegisterLoginComponent;
import org.projects.shoppingmall.bridge.abst.RegisterLoginComponent;
import org.projects.shoppingmall.bridge.abst.factory.RegisterLoginComponentFactory;
import org.projects.shoppingmall.bridge.function.RegisterLoginByDefault;
import org.projects.shoppingmall.bridge.function.RegisterLoginByPhone;
import org.projects.shoppingmall.pojo.UserInfo;
import org.projects.shoppingmall.pojo.dto.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {


  public String login(LoginRequest loginRequest) {
    AbstractRegisterLoginComponent component = null;
    if (StringUtils.isNotBlank(loginRequest.getUserName())) {
      component = RegisterLoginComponentFactory.getComponent("Default");
    }
    if (StringUtils.isNotBlank(loginRequest.getPhone())) {
      component = RegisterLoginComponentFactory.getComponent("Phone");
    }
    if (component == null) {
      throw new IllegalArgumentException("用户名或手机号不能为空");
    }
    return component.login(loginRequest);
  }

  public String register(UserInfo userInfo) {
    AbstractRegisterLoginComponent component = null;
    if (StringUtils.isNotBlank(userInfo.getUserName())) {
      component = RegisterLoginComponentFactory.getComponent("Default");
    }
    if (StringUtils.isNotBlank(userInfo.getPhone())) {
      component = RegisterLoginComponentFactory.getComponent("Phone");
    }
    if (component == null) {
      throw new IllegalArgumentException("用户名或手机号不能为空");
    }
    return component.register(userInfo);
  }

}
