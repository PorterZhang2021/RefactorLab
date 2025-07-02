package org.projects.shoppingmall.bridge.abst;

import org.projects.shoppingmall.bridge.function.RegisterLoginFuncInterface;
import org.projects.shoppingmall.pojo.UserInfo;
import org.projects.shoppingmall.pojo.dto.LoginRequest;

public class RegisterLoginComponent extends AbstractRegisterLoginComponent {
  public RegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
    super(funcInterface);
  }

  @Override
  public String login(LoginRequest loginRequest) {
    return funcInterface.login(loginRequest);
  }

  @Override
  public String register(UserInfo userInfo) {
    return funcInterface.register(userInfo);
  }

  @Override
  public boolean checkUserExists(UserInfo userInfo) {
    return funcInterface.checkUserExists(userInfo);
  }

}
