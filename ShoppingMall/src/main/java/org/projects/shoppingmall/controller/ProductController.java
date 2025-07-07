package org.projects.shoppingmall.controller;

import org.projects.shoppingmall.pojo.dto.AddProductRequest;
import org.projects.shoppingmall.pojo.dto.DeleteProductRequest;
import org.projects.shoppingmall.pojo.dto.LoginRequest;
import org.projects.shoppingmall.pojo.write.Product;
import org.projects.shoppingmall.service.ProductService;
import org.projects.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
  @Autowired
  private ProductService productService;

  @PostMapping("/fetchAllItems")
  public Product fetchAllItems() {
    return productService.fetchAllItems();
  }

  @PostMapping("/addItem")
  public Product addItem(@RequestBody AddProductRequest addProductRequest) {
    return productService.addItem(addProductRequest);
  }

  @PostMapping("/deleteItem")
  public Product deleteItem(@RequestBody DeleteProductRequest deleteProductRequest) {
    return productService.deleteItem(deleteProductRequest);
  }
}
