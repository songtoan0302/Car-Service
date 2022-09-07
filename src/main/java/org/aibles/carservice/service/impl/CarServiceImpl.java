package org.aibles.carservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.carservice.dto.request.CreateCarRequest;
import org.aibles.carservice.dto.request.UpdateCarRequest;
import org.aibles.carservice.dto.response.CarResponseDTO;
import org.aibles.carservice.entity.Car;
import org.aibles.carservice.exceptions.NotFoundException;
import org.aibles.carservice.repository.CarRepository;
import org.aibles.carservice.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class CarServiceImpl implements CarService {

  private final CarRepository repository;
  private final ModelMapper modelMapper ;

  public CarServiceImpl(CarRepository repository, ModelMapper modelMapper) {
    this.repository = repository;
    this.modelMapper = modelMapper;
  }

  /**
   * create a car
   *
   * @param request
   * @return CarResponse
   */
  @Override
  @Transactional
  public CarResponseDTO create(CreateCarRequest request) {
    log.info("(create)carCreate: {}", request);
    var car = modelMapper.map(request, Car.class);
    car.validate();
    var carResponseDTO = modelMapper.map(repository.save(car), CarResponseDTO.class);
    carResponseDTO.validate();
    return carResponseDTO;
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
   * @param carUpdate
   * @param id
   * @return CarResponse
   */
  @CachePut(value = "car", key = "#id")
  @Override
  @Transactional
  public CarResponseDTO update(String id, UpdateCarRequest carUpdate) {
    log.info("(update)carUpdate: {}, id: {} ", carUpdate, id);
    var car = repository.findById(id)
            .map(exist -> modelMapper.map(carUpdate, Car.class))
            .orElseThrow(() -> {throw new NotFoundException(id);});
    car.setId(id);
    car.validate();
    car = repository.save(car);
    var response = modelMapper.map(car, CarResponseDTO.class);
    response.validate();
    return response;
  }

  /**
   * get a car by id
   *
   * @param id
   * @return CarResponse
   */
  @Cacheable(value = "car", key = "#id")
  @Override
  @Transactional(readOnly = true)
  public CarResponseDTO get(String id) {
    log.info("(get)id: {} ", id);
    var car =
        repository
            .findById(id)
            .orElseThrow(
                () -> {
                  throw new NotFoundException(id);
                });
    car.validate();
    var response =modelMapper.map(car, CarResponseDTO.class);
    response.validate();
    return response;
  }

  /**
   * get all car
   *
   * @param pageable
   * @return Page<CarResponseDetails>
   */
  @Override
  @Transactional(readOnly = true)
  public Page<CarResponseDTO> list(Pageable pageable) {
    log.info("(list)pageable: {}", pageable);
    Page<Car> responseDTOPage = repository.findAll(pageable);
    return mapPage(responseDTOPage);
  }

  private Page<CarResponseDTO> mapPage(Page<Car> pageIn) {
    return pageIn.map(exist -> modelMapper.map(exist, CarResponseDTO.class));
  }
}
