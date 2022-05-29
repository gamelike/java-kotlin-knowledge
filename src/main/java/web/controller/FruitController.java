package web.controller;

import annotation.demo.Fruit;
import annotation.demo.FruitResolve;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gjd
 */
@RequestMapping("fruit")
@Controller
public class FruitController {

  @GetMapping
  @ResponseBody
  public Fruit getFruit(){
    try {
      Fruit fruit = FruitResolve.loadProperty(Fruit.class);
      fruit.setType(Fruit.FruitType.APPLE);
      // BEAN Property Writer 修改序列化后的值
      // find serializer to use
      return fruit;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
