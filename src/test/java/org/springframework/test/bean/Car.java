package org.springframework.test.bean;

import lombok.Data;
import org.springframework.context.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author gjd3
 */
@Data
@Component
public class Car {
  @Value("${brand}")
  private String brand;
}
