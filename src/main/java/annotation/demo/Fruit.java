package annotation.demo;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gjd
 */
@Data
public class Fruit {
  @FruitName("apple")
  String name;

  @FruitName(price = 20.3)
  Double price;

  FruitType type;

  public enum FruitType {
    APPLE("apple"),
    BANANA("banana");
    String name;

    FruitType(String apple) {
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
