package com.productmangement.api.mapper;

import com.productmangement.api.dto.ProductRequestDTO;
import com.productmangement.api.model.EntityProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Convert DTO to Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "updatedBy",ignore = true)
    EntityProduct toEntity(ProductRequestDTO productRequestDTO);

}
