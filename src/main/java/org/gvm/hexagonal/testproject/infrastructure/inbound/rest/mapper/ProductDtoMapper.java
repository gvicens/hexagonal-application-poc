package org.gvm.hexagonal.testproject.infrastructure.inbound.rest.mapper;

import org.gvm.hexagonal.testproject.domain.Product;
import org.gvm.hexagonal.testproject.infrastructure.inbound.rest.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper {

    ProductDto toDto(Product product);

    List<ProductDto> toDtoList(List<Product> products);

    Product toProduct(ProductDto productDto);

    List<Product> toDomainList(List<ProductDto> productDtos);

}
