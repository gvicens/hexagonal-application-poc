package org.gvm.hexagonal.testproject.application.ports.secondary;

import org.gvm.hexagonal.testproject.domain.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository {

    List<Product> getProducts(LocalDateTime applicationDateTime, Integer companyId, Integer productId);

    List<Product> getPriorityProduct(LocalDateTime applicationDateTime, Integer companyId, Integer productId);

    void createProduct(Product product);
}
