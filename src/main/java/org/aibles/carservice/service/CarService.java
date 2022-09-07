package org.aibles.carservice.service;

import org.aibles.carservice.dto.request.CreateCarRequest;
import org.aibles.carservice.dto.request.UpdateCarRequest;
import org.aibles.carservice.dto.response.CarResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {

  /**
   * create a car
   *
   * @param createCarRequest
   * @return CarResponse
   */
  CarResponseDTO create(CreateCarRequest createCarRequest);

  /**
   * delete car by id and no return
   *
   * @param id
   */
  void delete(String id);

  /** delete all car no return no param */
  void deleteAll();

  /**
   * get a car by id
   *
   * @param id
   * @return CarResponse
   */
  CarResponseDTO get(String id);

  /**
   * get all car
   *
   * @param pageable
   * @return Page<CarResponseDetails>
   */
  Page<CarResponseDTO> list(Pageable pageable);

  /**
   * update a car
   *
   * @param carUpdate
   * @param id
   * @return CarResponse
   */
  CarResponseDTO update(String id, UpdateCarRequest carUpdate);
}
