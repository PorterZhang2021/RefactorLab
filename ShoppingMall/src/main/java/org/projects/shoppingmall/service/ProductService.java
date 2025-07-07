package org.projects.shoppingmall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.projects.shoppingmall.pojo.ProductItem;
import org.projects.shoppingmall.pojo.write.Product;
import org.projects.shoppingmall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public Product fetchAllItems() {
    List<ProductItem> productItems = productRepository.findAll();
    Map<Integer, Product> productMap = new HashMap<>();

    // 实际当中这个部分还有对应的缓存逻辑，但是这里只有构建新列表的逻辑，把之前的省略了


    for (ProductItem productItem : productItems) {
      Product product = Product.builder()
          .id(productItem.getId())
          .name(productItem.getName())
          .products(new ArrayList<>())
          .build();
      productMap.put(productItem.getId(), product);
    }

    for (ProductItem productItem : productItems) {
      int pid = productItem.getPid();
      Product product = productMap.get(productItem.getId());
      if (pid != 0) {
        Product parentProduct = productMap.get(pid);
        List<Product> products = parentProduct.getProducts();
        products.add(product);
      }
    }

    return productMap.get(1);
  }

}
