package com.corporate.project.laza_api.mapper;

import com.corporate.project.laza_api.model.dto.CreateReactDto;
import com.corporate.project.laza_api.model.dto.ReactDto;
import com.corporate.project.laza_api.model.entity.React;
import org.mapstruct.BeanMapping;
import org.mapstruct.*;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReactMapper {
  List<ReactDto> mapReactListToReactDtoList(List<React> reactList);

  ReactDto mapReactToReactDto(React react);

  React mapCreateReactDtoToReact(CreateReactDto createReactDto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void mapForPartialUpdate(@MappingTarget React react, CreateReactDto createReactDto);

}
