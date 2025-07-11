package org.projects.shoppingmall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.projects.shoppingmall.pojo.ProductItem;
import org.projects.shoppingmall.pojo.dto.AddProductRequest;
import org.projects.shoppingmall.pojo.dto.DeleteProductRequest;
import org.projects.shoppingmall.pojo.write.ProductNode;
import org.projects.shoppingmall.repository.ProductRepository;
import org.projects.shoppingmall.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired
  private TreeUtils treeUtils;

  public ProductNode fetchAllItems() {
    return treeUtils.getProductTree();
  }

  public ProductNode addItem(AddProductRequest addProductRequest) {
    return null;
  }

  public ProductNode deleteItem(DeleteProductRequest deleteProductRequest) {
    return null;
  }
}
