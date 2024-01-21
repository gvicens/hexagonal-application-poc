package org.gvm.hexagonal.testproject.application;

import org.gvm.hexagonal.testproject.domain.Product;
import org.gvm.hexagonal.testproject.application.ports.primary.ProductService;
import org.gvm.hexagonal.testproject.application.ports.secondary.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getProducts(LocalDateTime applicationDateTime, Integer companyId, Integer productId, boolean isPriority) {
        // TODO: some additional business logic here

        if (!isPriority) {
            return productRepository.getProducts(applicationDateTime, companyId, productId);
        }
        else {

            return productRepository.getPriorityProduct(applicationDateTime, companyId, productId);
        }
    }


    public void createProduct(Product product) {
        productRepository.createProduct(product);
    }
}
