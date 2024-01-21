package org.gvm.hexagonal.testproject.application.ports.primary;

import org.gvm.hexagonal.testproject.domain.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {

    List<Product> getProducts(LocalDateTime applicationDateTime, Integer companyId, Integer productId, boolean isPriority);

    void createProduct(Product product);
}
