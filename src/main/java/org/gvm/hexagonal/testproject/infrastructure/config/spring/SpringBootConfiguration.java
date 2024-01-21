package org.gvm.hexagonal.testproject.infrastructure.config.spring;

import org.gvm.hexagonal.testproject.application.ProductServiceImpl;
import org.gvm.hexagonal.testproject.application.ports.secondary.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringBootConfiguration {

    @Bean
    public ProductServiceImpl productServiceImpl(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }
}
