package org.projects.shoppingmall.bridge.abst;

import org.projects.shoppingmall.bridge.function.RegisterLoginFuncInterface;
import org.projects.shoppingmall.pojo.UserInfo;
import org.projects.shoppingmall.pojo.dto.LoginRequest;

public abstract class AbstractRegisterLoginComponent {
  protected RegisterLoginFuncInterface funcInterface;

  public AbstractRegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
    validate(funcInterface);
    this.funcInterface = funcInterface;
  }

  protected void validate(RegisterLoginFuncInterface funcInterface) {
    if (!(funcInterface instanceof RegisterLoginFuncInterface)) {
      throw new IllegalArgumentException("funcInterface must be an instance of RegisterLoginFuncInterface");
    }
  }

  public String login(LoginRequest loginRequest) {
    throw new UnsupportedOperationException("login() is not implemented");
  }

  ;

  public String register(UserInfo userInfo) {
    throw new UnsupportedOperationException("register() is not implemented");
  }

  ;

  public boolean checkUserExists(UserInfo userInfo) {
    throw new UnsupportedOperationException("checkUserExists() is not implemented");
  }

  public String validUserLogin(UserInfo userInfo) {
    throw new UnsupportedOperationException("validUserLogin() is not implemented");
  }
}
