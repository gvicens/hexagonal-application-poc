package org.gvm.hexagonal.testproject.infrastructure.outbound.h2.mapper;

import org.gvm.hexagonal.testproject.domain.Product;
import org.gvm.hexagonal.testproject.infrastructure.outbound.h2.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    Product toDomain(ProductEntity productEntity);

    List<Product> toDomainList(List<ProductEntity> productEntities);

    ProductEntity toEntity(Product product);

    List<ProductEntity> toEntityList(List<Product> products);

}
