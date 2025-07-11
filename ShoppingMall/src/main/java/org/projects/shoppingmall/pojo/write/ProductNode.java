package org.projects.shoppingmall.pojo.write;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class ProductNode {
  private int id;
  private String name;
  private List<ProductNode> children = new ArrayList<>();

  public ProductNode(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public List<ProductNode> children() {
    if (children == null) {
      children = new ArrayList<>();
    }
    return children;
  }

  public boolean isExist(int id) {
    return children().stream().anyMatch(child -> child.getId() == id);
  }

  public void addChild(ProductNode productNode) {
    children().add(productNode);
  }

  public void deleteChild(int id) {
    children().removeIf(child -> child.getId() == id);
  }

}
