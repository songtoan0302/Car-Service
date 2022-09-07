package org.aibles.carservice.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author ToanNS
 */
@Data
public class UpdateCarRequest extends CreateCarRequest {
  @NotBlank(message = "id can't blank")
  private String id;
}
