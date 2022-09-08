package org.aibles.carservice.dto.request;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.aibles.carservice.entity.Car;
import org.aibles.carservice.exceptions.BadRequestException;

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

  public Car toEntity() {
    Car car = new Car();
    car.setName(this.name);
    car.setBrand(this.brand);
    car.setEngineType(this.engineType);
    car.setColor(this.color);
    car.setPrice(this.price);
    car.setAmount(this.amount);
    return car;
  }

  public void validateDataTypePrimitive(){
    Map<String, String> valid = new HashMap<>();
    if(this.price == 0) {
      valid.put("price", "price not equals to 0!");
    }
    if(this.amount == 0) {
      valid.put("amount", "amount not equals to 0!");
    }
    if (!valid.isEmpty()) {
      throw new BadRequestException(valid);
    }
  }
}
