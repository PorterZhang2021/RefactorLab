package org.projects.shoppingmall.bridge.function;

import org.projects.shoppingmall.pojo.UserInfo;
import org.projects.shoppingmall.pojo.dto.LoginRequest;

public interface RegisterLoginFuncInterface {
  public String login(LoginRequest loginRequest);

  public String register(UserInfo userInfo);

  public boolean checkUserExists(UserInfo userInfo);


}
