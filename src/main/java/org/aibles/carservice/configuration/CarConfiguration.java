package org.aibles.carservice.configuration;

import org.aibles.carservice.repository.CarRepository;
import org.aibles.carservice.service.CarService;
import org.aibles.carservice.service.impl.CarServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author toanns
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.aibles.carservice.repository")
@ComponentScan(basePackages = "org.aibles.carservice.reporitory")
public class CarConfiguration {

  @Bean
  public CarService carService(CarRepository carRepository,ModelMapper modelMapper){
    return new CarServiceImpl(carRepository, modelMapper);
  }

}
