package org.aibles.carservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.carservice.dto.request.CreateCarRequest;
import org.aibles.carservice.dto.request.UpdateCarRequest;
import org.aibles.carservice.dto.response.CarResponseDTO;
import org.aibles.carservice.entity.Car;
import org.aibles.carservice.exceptions.BadRequestException;
import org.aibles.carservice.exceptions.NotFoundException;
import org.aibles.carservice.repository.CarRepository;
import org.aibles.carservice.service.CarService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class CarServiceImpl implements CarService {

  private final CarRepository repository;

  public CarServiceImpl(CarRepository repository) {
    this.repository = repository;
  }

  /**
   * create a car
   *
   * @param request
   * @return CarResponseDTO
   */
  @Override
  @Transactional
  public CarResponseDTO create(CreateCarRequest request) {
    log.info("(create)carCreate: {}", request);
    request.validateDataTypePrimitive();

    var car = request.toEntity();
    car.validate();

    var response =  CarResponseDTO.toDTO(repository.save(car));
    response.validate();
    return response;
  }

  /**
   * delete car by id and no return
   *
   * @param id
   */
  @CacheEvict(value = "car", key = "#id")
  @Override
  @Transactional
  public void delete(String id) {
    log.info("(delete)");
    if (repository.existsById(id)) {
      throw new NotFoundException(id);
    }
    repository.deleteById(id);
  }

  /**
   * delete all car no return no param
   */
  @CacheEvict
  @Override
  @Transactional
  public void deleteAll() {
    log.info("(deleteAll)");
    repository.deleteAll();
  }

  /**
   * update a car
   *
   * @param updateCarRequest
   * @return CarResponseDTO
   */
  @CachePut(value = "car", key = "#id")
  @Override
  @Transactional
  public CarResponseDTO update(UpdateCarRequest updateCarRequest) {
    log.info("(update)carUpdate: {}", updateCarRequest);
    updateCarRequest.validateDataTypePrimitive();
    return repository.findById(updateCarRequest.getId())
        .map(exist -> {
          exist = updateCarRequest.toEntity();
          exist.validate();
          exist = repository.save(exist);

          var response = CarResponseDTO.toDTO(exist);
          response.validate();
          return response;
        })
        .orElseThrow(() -> {throw new NotFoundException(updateCarRequest.getId());});

  }

  /**
   * get a car by id
   *
   * @param id
   * @return CarResponseDTO
   */
  @Cacheable(value = "car", key = "#id")
  @Override
  @Transactional(readOnly = true)
  public CarResponseDTO get(String id) {
    log.info("(get)id: {} ", id);
    return repository
        .findById(id)
        .map(exist -> {
          var response = CarResponseDTO.toDTO(exist);
          response.validate();
          return response;
        })
        .orElseThrow(() -> {throw new NotFoundException(id);});
  }

  /**
   * get all car
   *
   * @param pageable
   * @return Page<CarResponseDTO>
   */
  @Override
  @Transactional(readOnly = true)
  public Page<CarResponseDTO> list(Pageable pageable) {
    log.info("(list)pageable: {}", pageable);
    Page<Car> responseDTOPage = repository.findAll(pageable);
    return mapPage(responseDTOPage);
  }

  private Page<CarResponseDTO> mapPage(Page<Car> pageIn) {
    return pageIn.map(exist -> {
      var response = CarResponseDTO.toDTO(exist);
      response.validate();
      return response;
    });
  }
}
