package org.aibles.carservice.dto.response;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author ToanNS
 */
@Data
public class CarResponse {
  private String name;
  private String brand;
  private String engineType;
  private String color;
  private long price;
  private int amount;
}
