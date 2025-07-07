package org.projects.shoppingmall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.projects.shoppingmall.pojo.ProductItem;
import org.projects.shoppingmall.pojo.dto.AddProductRequest;
import org.projects.shoppingmall.pojo.dto.DeleteProductRequest;
import org.projects.shoppingmall.pojo.write.Product;
import org.projects.shoppingmall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CacheManager cacheManager;

  public Product fetchAllItems() {

    return getProductTree();
  }

  private Product getProductTree() {
    // 实际当中这个部分还有对应的缓存逻辑，但是这里只有构建新列表的逻辑，把之前的省略了
    Cache defaultCache = cacheManager.getCache("default");
    if (defaultCache.get("productTree") != null) {
      return defaultCache.get("productTree", Product.class);
    }

    Product product = buildProductTree();
    defaultCache.put("productTree", product);
    return product;
  }

  private Product buildProductTree() {
    List<ProductItem> productItems = productRepository.findAll();
    Map<Integer, Product> productMap = new HashMap<>();
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

  public Product addItem(AddProductRequest addProductRequest) {
    Product productTree = getProductTree();
    List<Product> products = new ArrayList<>();
    products.add(productTree);
    while (!products.isEmpty()) {
      Product product = products.remove(0);
      List<Product> childProductList = product.getProducts();

      if (product.getName().equalsIgnoreCase(addProductRequest.getName())) {
        break;
      }

      if (product.getId() == addProductRequest.getPid()) {
        ProductItem productItem = new ProductItem();
        productItem.setName(addProductRequest.getName());
        productItem.setPid(addProductRequest.getPid());
        ProductItem saveItem = productRepository.save(productItem);

        Product addProduct = Product.builder()
            .id(saveItem.getId())
            .name(saveItem.getName())
            .products(new ArrayList<>())
            .build();

        childProductList.add(addProduct);
        break;
      }

      products.addAll(childProductList);
    }
    return productTree;
  }

  public Product deleteItem(DeleteProductRequest deleteProductRequest) {
    ProductItem product = productRepository.findByName(deleteProductRequest.getName());
    Product productTree = getProductTree();
    if (product == null) {
      return productTree;
    }
    int pid = product.getPid();
    List<Product> products = new ArrayList<>();
    products.add(productTree);
    while (!products.isEmpty()) {
      Product checkProduct = products.remove(0);
      List<Product> childProductList = checkProduct.getProducts();

      if (checkProduct.getId() == pid) {
        childProductList.removeIf(p -> p.getName().equalsIgnoreCase(deleteProductRequest.getName()));
        break;
      }

      products.addAll(childProductList);
    }
    productRepository.delete(product);
    return productTree;
  }
}
