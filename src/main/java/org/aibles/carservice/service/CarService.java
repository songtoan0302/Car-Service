package org.aibles.carservice.service;

import org.aibles.carservice.dto.request.CarCreate;
import org.aibles.carservice.dto.request.CarUpdate;
import org.aibles.carservice.dto.response.CarResponse;
import org.aibles.carservice.dto.response.CarResponseDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {

  CarResponse create(CarCreate carCreate);

  void delete(String id);

  void deleteAll();

  CarResponse get(String id);

  Page<CarResponseDetails> list(Pageable pageable);

  CarResponse update(CarUpdate carUpdate, String id);
}
