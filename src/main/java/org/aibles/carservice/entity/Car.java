package org.aibles.carservice.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.aibles.carservice.validation.ModelValidator;

@Data
@Entity
@Table(name = "car")
public class Car extends ModelValidator<Car> {

  @Id
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

  @PrePersist
  private void prePersistId() {
    this.id = this.id == null ? UUID.randomUUID().toString() : id;
  }
}
