package org.springframework.test.bean;

import lombok.Data;
import org.springframework.context.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author gjd3
 */
@Data
@Component
public class Car {
  @Value("${brand}")
  private String brand;

  private int price;

  private LocalDate produceDate;
}
