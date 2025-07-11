package org.projects.shoppingmall.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.projects.shoppingmall.pojo.ProductItem;
import org.projects.shoppingmall.pojo.dto.AddProductRequest;
import org.projects.shoppingmall.pojo.dto.DeleteProductRequest;
import org.projects.shoppingmall.pojo.write.ProductNode;
import org.projects.shoppingmall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class TreeUtils {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CacheManager cacheManager;

  public ProductNode getProductTree() {
    // 实际当中这个部分还有对应的缓存逻辑，但是这里只有构建新列表的逻辑，把之前的省略了
    Cache defaultCache = cacheManager.getCache("default");
    if (defaultCache.get("productTree") != null) {
      return defaultCache.get("productTree", ProductNode.class);
    }

    ProductNode productNode = buildProductTree();
    defaultCache.put("productTree", productNode);
    return productNode;
  }

  public ProductNode buildProductTree() {
    List<ProductItem> productItems = productRepository.findAll();
    Map<Integer, ProductNode> productMap = new HashMap<>();
    for (ProductItem productItem : productItems) {
      ProductNode productNode = new ProductNode(productItem.getId(), productItem.getName());
      productMap.put(productItem.getId(), productNode);
    }

    for (ProductItem productItem : productItems) {
      int pid = productItem.getPid();
      ProductNode productNode = productMap.get(productItem.getId());
      if (pid != 0) {
        ProductNode parentProductNode = productMap.get(pid);
        parentProductNode.addChild(productNode);
      }
    }

    return productMap.get(1);
  }

  public ProductNode addItem(AddProductRequest addProductRequest) {
    ProductNode productNodeTree = getProductTree();
    List<ProductNode> productNodes = new ArrayList<>();
    productNodes.add(productNodeTree);
    while (!productNodes.isEmpty()) {
      ProductNode checkProductNode = productNodes.remove(0);

      if (checkProductNode.getName().equalsIgnoreCase(addProductRequest.getName())) {
        break;
      }

      if (checkProductNode.getId() == addProductRequest.getPid()) {
        ProductItem productItem = new ProductItem();
        productItem.setName(addProductRequest.getName());
        productItem.setPid(addProductRequest.getPid());
        ProductItem saveItem = productRepository.save(productItem);
        checkProductNode.addChild(new ProductNode(saveItem.getId(), saveItem.getName()));
        break;
      }
    }
    return productNodeTree;
  }

  public ProductNode deleteItem(int id) {
    ProductItem product = productRepository.findById(id);
    ProductNode productNodeTree = getProductTree();
    if (product == null) {
      return productNodeTree;
    }
    List<ProductNode> productNodes = new ArrayList<>();
    productNodes.add(productNodeTree);
    while (!productNodes.isEmpty()) {
      ProductNode checkProductNode = productNodes.remove(0);

      if (checkProductNode.isExist(product.getId())) {
        checkProductNode.deleteChild(product.getId());
        break;
      }

      productNodes.addAll(checkProductNode.children());
    }
    productRepository.delete(product);
    return productNodeTree;
  }
}
