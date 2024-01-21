package org.gvm.hexagonal.testproject.infrastructure.outbound.h2;

import lombok.RequiredArgsConstructor;
import org.gvm.hexagonal.testproject.infrastructure.outbound.h2.mapper.ProductEntityMapper;
import org.gvm.hexagonal.testproject.domain.Product;
import org.gvm.hexagonal.testproject.application.ports.secondary.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductDBService implements ProductRepository {

    private final ProductJpaH2Repository productJpaH2Repository;

    private final ProductEntityMapper productEntityMapper;

    public List<Product> getProducts(
            LocalDateTime applicationDateTime,
            Integer companyId,
            Integer productId) {
        return productEntityMapper.toDomainList(productJpaH2Repository.getProducts(applicationDateTime, companyId, productId));
    }


    public List<Product> getPriorityProduct(
            LocalDateTime applicationDateTime,
            Integer companyId,
            Integer productId) {

        return productEntityMapper.toDomainList(productJpaH2Repository.getPriorityProduct(applicationDateTime, companyId, productId));
    }

    public void createProduct(Product product) {
        productJpaH2Repository.save(productEntityMapper.toEntity(product));
    }
}
