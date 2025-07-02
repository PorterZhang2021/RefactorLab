package org.projects.shoppingmall.bridge.abst.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.projects.shoppingmall.bridge.abst.AbstractRegisterLoginComponent;
import org.projects.shoppingmall.bridge.abst.RegisterLoginComponent;
import org.projects.shoppingmall.bridge.function.RegisterLoginFuncInterface;

public class RegisterLoginComponentFactory {
  public static Map<String, AbstractRegisterLoginComponent> componentMap = new ConcurrentHashMap<>();
  public static Map<String, RegisterLoginFuncInterface> funcMap = new ConcurrentHashMap<>();

  public static AbstractRegisterLoginComponent getComponent(String type) {
    AbstractRegisterLoginComponent component = componentMap.get(type);
    if (component == null) {
      synchronized (componentMap) {
        component = componentMap.get(type);
        if (component == null) {
          component = new RegisterLoginComponent(funcMap.get(type));
          componentMap.put(type, component);
        }
      }
    }
    return component;
  }
}
