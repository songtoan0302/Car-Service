package org.aibles.carservice.dto.mapper;

import org.modelmapper.ModelMapper;

public class ModelMapperSingleton {

  private static final ModelMapper instance = new ModelMapper();

  private ModelMapperSingleton() {
  }

  public static ModelMapper getInstance() {
    return instance;
  }
}
