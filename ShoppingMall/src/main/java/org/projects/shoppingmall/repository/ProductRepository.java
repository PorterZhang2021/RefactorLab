package org.projects.shoppingmall.repository;

import java.util.List;
import org.projects.shoppingmall.pojo.ProductItem;
import org.projects.shoppingmall.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductItem, Integer> {
  ProductItem findByName(String name);

  List<ProductItem> findByPid(int pid);

  ProductItem findById(int id);


  List<ProductItem> findAll();
}
