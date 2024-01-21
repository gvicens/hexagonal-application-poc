package org.gvm.hexagonal.testproject.infrastructure.inbound.rest;

import lombok.RequiredArgsConstructor;
import org.gvm.hexagonal.testproject.application.ports.primary.ProductService;
import org.gvm.hexagonal.testproject.infrastructure.inbound.rest.mapper.ProductDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductDtoMapper productDtoMapper;


    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(
            @RequestParam(name="applicationDateTime")LocalDateTime applicationDateTime,
            @RequestParam(name="companyId") Integer companyId,
            @RequestParam(name="productId") Integer productId,
            @RequestParam(name="isPriority", required = false, defaultValue = "false") boolean isPriority
            ) {

        List<ProductDto> productList = productDtoMapper.toDtoList(productService.getProducts(applicationDateTime, companyId, productId, isPriority));

        if (productList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.of(Optional.of(productList));
        }

    }

    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        productService.createProduct(productDtoMapper.toProduct(product));

        return ResponseEntity.ok().build();
    }
}
