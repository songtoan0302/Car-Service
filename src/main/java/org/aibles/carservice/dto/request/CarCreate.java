package org.aibles.carservice.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author ToanNS
 */
@Data
public class CarCreate {
  @NotBlank
  private String name;
  @NotBlank
  private String brand;
  @NotBlank
  private String engineType;
  @NotBlank
  private String color;
  private long price;
  private int amount;
}
