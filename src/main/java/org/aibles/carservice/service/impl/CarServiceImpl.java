package org.aibles.carservice.service.impl;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.aibles.carservice.dto.request.CarCreate;
import org.aibles.carservice.dto.request.CarUpdate;
import org.aibles.carservice.dto.response.CarResponse;
import org.aibles.carservice.dto.response.CarResponseDetails;
import org.aibles.carservice.entity.Car;
import org.aibles.carservice.exceptions.InternalServerException;
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

  private final CarRepository carRepository;
  private final ModelMapper modelMapper;

  public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
    this.carRepository = carRepository;
    this.modelMapper = modelMapper;
  }

  /**
   * create a car
   *
   * @param carCreate
   * @return CarResponse
   */
  @Override
  @Transactional
  public CarResponse create(CarCreate carCreate) {
    log.info("(create)carCreate: {}", carCreate);
    Car car = modelMapper.map(carCreate, Car.class);
    if (Objects.isNull(car)) throw new InternalServerException("Mapping fails!");
    return modelMapper.map(carRepository.save(car), CarResponse.class);
  }

  /**
   * delete car by id and no return
   *
   * @param id
   */
  @Cacheable(value = "car", key = "#id")
  @Override
  @Transactional
  public void delete(String id) {
    log.info("(delete)");
    carRepository.deleteById(id);
  }

  /** delete all car no return no param */
  @CacheEvict
  @Override
  @Transactional
  public void deleteAll() {
    log.info("(deleteAll)");
    carRepository.deleteAll();
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
  public CarResponse update(CarUpdate carUpdate, String id) {
    log.info("(update)carUpdate: {}, id: {} ", carUpdate, id);
    Car car =
        carRepository
            .findById(id)
            .orElseThrow(
                () -> {
                  throw new NotFoundException(id);
                });
    Car carUpdated = modelMapper.map(carUpdate, Car.class);
    if (Objects.isNull(carUpdated)) throw new InternalServerException("Mapping fails");
    carUpdated.setId(car.getId());
    carUpdated = carRepository.save(carUpdated);
    return modelMapper.map(carUpdated, CarResponse.class);
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
  public CarResponse get(String id) {
    log.info("(get)id: {} ", id);
    Car car =
        carRepository
            .findById(id)
            .orElseThrow(
                () -> {
                  throw new NotFoundException(id);
                });
    return modelMapper.map(car, CarResponse.class);
  }

  /**
   * get all car
   *
   * @param pageable
   * @return Page<CarResponseDetails>
   */
  @Override
  @Transactional(readOnly = true)
  public Page<CarResponseDetails> list(Pageable pageable) {
    log.info("(list)pageable: {}", pageable);
    return carRepository.findAll(pageable).map(car -> modelMapper.map(car, CarResponseDetails.class));
  }
}
