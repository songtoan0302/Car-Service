package org.aibles.carservice.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.aibles.carservice.entity.Car;

/**
 * @author ToanNS
 */
@Data
public class UpdateCarRequest extends CreateCarRequest {
  @NotBlank(message = "id can't blank")
  private String id;

  public Car toEntity() {
    Car car = new Car();
    car.setId(this.id);
    car.setName(this.getName());
    car.setBrand(this.getBrand());
    car.setEngineType(this.getEngineType());
    car.setColor(this.getColor());
    car.setPrice(this.getPrice());
    car.setAmount(this.getAmount());
    return car;
  }
}
