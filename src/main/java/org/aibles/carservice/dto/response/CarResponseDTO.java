package org.aibles.carservice.dto.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.aibles.carservice.entity.Car;
import org.aibles.carservice.validation.ModelValidator;

/**
 * @author ToanNS
 */
@Data
public class CarResponseDTO extends ModelValidator<CarResponseDTO> {
  @NotBlank
  private String id;
  @NotBlank
  private String name;
  @NotBlank
  private String brand;
  @NotBlank
  private String engineType;
  @NotBlank
  private String color;
  @NotNull
  private Long price;
  @NotNull
  private Integer amount;

  public static CarResponseDTO toDTO(Car car) {
    CarResponseDTO carResponseDTO = new CarResponseDTO();
    carResponseDTO.setId(car.getId());
    carResponseDTO.setName(car.getName());
    carResponseDTO.setBrand(car.getBrand());
    carResponseDTO.setEngineType(car.getEngineType());
    carResponseDTO.setColor(car.getColor());
    carResponseDTO.setPrice(car.getPrice());
    carResponseDTO.setAmount(car.getAmount());
    return carResponseDTO;
  }
}
