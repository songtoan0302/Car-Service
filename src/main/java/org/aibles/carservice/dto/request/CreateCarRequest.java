package org.aibles.carservice.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author ToanNS
 */
@Data
public class CreateCarRequest {

  @NotBlank(message = "name can't blank!")
  private String name;
  @NotBlank(message = "brand can't blank!")
  private String brand;
  @NotBlank(message = "engine type can't blank!")
  private String engineType;
  @NotBlank(message = "color can't blank!")
  private String color;
  private long price;
  private int amount;
}
