package org.aibles.carservice.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.carservice.dto.request.CreateCarRequest;
import org.aibles.carservice.dto.request.UpdateCarRequest;
import org.aibles.carservice.dto.response.CarResponseDTO;
import org.aibles.carservice.service.CarService;
import org.aibles.carservice.utils.paging.PagingReq;
import org.aibles.carservice.utils.paging.PagingRes;
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

  private final CarService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CarResponseDTO add(@RequestBody @Valid CreateCarRequest createCarRequest) {
    log.info("(create)carCreate: {}", createCarRequest);
    return service.create(createCarRequest);
  }

  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id") String id) {
    log.info("(delete)id: {} ", id);
    service.delete(id);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public void deleteAll() {
    log.info("(deleteAll)");
    service.deleteAll();
  }

  @GetMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public CarResponseDTO get(@PathVariable("id") String id) {
    log.info("(get)id: {}", id);
    return service.get(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public PagingRes<CarResponseDTO> list(@Validated() final PagingReq pagingReq) {
    log.info("(list)pagingReq: {}", pagingReq);
    return PagingRes.of(service.list(pagingReq.makePageable()));
  }

  @PutMapping(path = "{id}")
  public CarResponseDTO update(@PathVariable("id") String id,
      @RequestBody @Valid UpdateCarRequest carUpdate) {
    log.info("(update)id: {}, carUpdate: {}", id, carUpdate);
    return service.update(id, carUpdate);
  }
}
