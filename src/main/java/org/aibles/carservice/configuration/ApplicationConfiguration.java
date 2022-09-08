package org.aibles.carservice.configuration;

import org.aibles.carservice.repository.CarRepository;
import org.aibles.carservice.service.CarService;
import org.aibles.carservice.service.impl.CarServiceImpl;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author toanns
 */
@Configuration
@ComponentScan(basePackages = {"org.aibles.carservice.reporitory"})
@EnableJpaRepositories(basePackages = {"org.aibles.carservice.repository"})
@EnableCaching
public class ApplicationConfiguration {

  @Bean
  public CarService carService(CarRepository repository) {
    return new CarServiceImpl(repository);
  }

}
