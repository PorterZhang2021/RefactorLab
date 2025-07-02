package org.projects.shoppingmall.repository;

import org.projects.shoppingmall.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {

  UserInfo findByUserName(String username);

  UserInfo findByUserNameAndPassword(String username, String password);

}
