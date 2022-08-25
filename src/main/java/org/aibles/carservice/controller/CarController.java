package org.aibles.carservice.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.carservice.component.paging.PagingReq;
import org.aibles.carservice.component.paging.PagingRes;
import org.aibles.carservice.dto.request.CarCreate;
import org.aibles.carservice.dto.request.CarUpdate;
import org.aibles.carservice.dto.response.CarResponse;
import org.aibles.carservice.dto.response.CarResponseDetails;
import org.aibles.carservice.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ToanNS
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {

  private final CarService carService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CarResponse add(@RequestBody CarCreate carCreate) {
    log.info("(create)carDTO: {}", carCreate);
    return carService.create(carCreate);
  }

  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id") String id) {
    log.info("(delete)id: {} ", id);
    carService.delete(id);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public void deleteAll() {
    log.info("(deleteAll)");
    carService.deleteAll();
  }

  @GetMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public CarResponse get(@PathVariable("id") String id) {
    log.info("(get)id: {}", id);
    return carService.get(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public PagingRes<CarResponseDetails> list(@Validated() final PagingReq pagingReq) {
    log.info("(list)pagingReq: {}", pagingReq);
    return PagingRes.of(carService.list(pagingReq.makePageable()));
  }

  @PutMapping(path = "{id}")
  public CarResponse update(
      @RequestBody @Valid CarUpdate carUpdate, @PathVariable("id") String id) {
    log.info("(update)carDTO: {}, id: {}", carUpdate, id);
    return carService.update(carUpdate, id);
  }
}
