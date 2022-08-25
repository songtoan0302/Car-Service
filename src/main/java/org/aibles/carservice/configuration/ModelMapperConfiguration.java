package org.aibles.carservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ToanNS
 */
@Configuration
public class ModelMapperConfiguration {
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
