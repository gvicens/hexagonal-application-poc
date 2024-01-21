package org.gvm.hexagonal.testproject;


import org.gvm.hexagonal.testproject.application.ProductServiceImpl;
import org.gvm.hexagonal.testproject.application.ports.primary.ProductService;
import org.gvm.hexagonal.testproject.application.ports.secondary.ProductRepository;
import org.gvm.hexagonal.testproject.infrastructure.inbound.rest.ProductController;
import org.gvm.hexagonal.testproject.infrastructure.inbound.rest.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
class ProductControllerTest {

    private DateTimeFormatter dateTimeFormatter;

    @Autowired
    public ProductRepository productRepository;


    public ProductService productService;

    @Autowired
    public ProductController productController;


    @BeforeEach
    public void SetUp() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        productService = new ProductServiceImpl(productRepository);
    }

    // Test 1: petición a las 10:00 del día 14 del producto 35455 para la compañía 1 (ZARA)
    @Test
    void testMorningDay14() {
        ResponseEntity<List<ProductDto>> productResultEntity;
        List<ProductDto> productResult;
        LocalDateTime applicationDateTime = LocalDateTime.parse("2020/06/14 10:00", dateTimeFormatter);

        productResultEntity = productController.getProducts(applicationDateTime, 1, 35455, false);

        productResult = productResultEntity.getBody();

        Assertions.assertNotNull(productResult);
        Assertions.assertEquals(1, productResult.size());

        ProductDto productDto = productResult.get(0);
        Assertions.assertEquals(1, productDto.getCompanyId());
        Assertions.assertEquals(35455, productDto.getProductId());
        Assertions.assertEquals("2020-06-14T00:00", productDto.getStartDate().toString());

    }


    // Test 2: petición a las 16:00 del día 14 del producto 35455 para la compañía 1 (ZARA)
    @Test
    void testAfternoonDay14(){
        ResponseEntity<List<ProductDto>> productResultEntity;
        List<ProductDto> productResult;
        LocalDateTime applicationDateTime = LocalDateTime.parse("2020/06/14 16:00", dateTimeFormatter);

        productResultEntity = productController.getProducts(applicationDateTime, 1, 35455, false);
        productResult = productResultEntity.getBody();

        Assertions.assertNotNull(productResult);
        Assertions.assertEquals(2, productResult.size());

        ProductDto productDto = productResult.get(1);
        Assertions.assertEquals(1, productDto.getCompanyId());
        Assertions.assertEquals(35455, productDto.getProductId());
        Assertions.assertEquals("2020-06-14T15:00", productDto.getStartDate().toString());
    }


    // Test 3: petición a las 21:00 del día 14 del producto 35455 para la compañía 1 (ZARA)
    @Test
    void testNightDay14(){
        ResponseEntity<List<ProductDto>> productResultEntity;
        List<ProductDto> productResult;
        LocalDateTime applicationDateTime = LocalDateTime.parse("2020/06/14 21:00", dateTimeFormatter);

        productResultEntity = productController.getProducts(applicationDateTime, 1, 35455, false);
        productResult = productResultEntity.getBody();

        Assertions.assertNotNull(productResult);
        Assertions.assertEquals(1, productResult.size());

        ProductDto productDto = productResult.get(0);
        Assertions.assertEquals(1, productDto.getCompanyId());
        Assertions.assertEquals(35455, productDto.getProductId());
        Assertions.assertEquals("2020-06-14T00:00", productDto.getStartDate().toString());
    }


    // Test 4: petición a las 10:00 del día 15 del producto 35455 para la compañía 1 (ZARA)
    @Test
    void testMorningDay15(){
        ResponseEntity<List<ProductDto>> productResultEntity;
        List<ProductDto> productResult;
        LocalDateTime applicationDateTime = LocalDateTime.parse("2020/06/15 10:00", dateTimeFormatter);

        productResultEntity = productController.getProducts(applicationDateTime, 1, 35455, false);
        productResult = productResultEntity.getBody();

        Assertions.assertNotNull(productResult);
        Assertions.assertEquals(2, productResult.size());

        ProductDto productDto = productResult.get(1);
        Assertions.assertEquals(1, productDto.getCompanyId());
        Assertions.assertEquals(35455, productDto.getProductId());
        Assertions.assertEquals("2020-06-15T00:00", productDto.getStartDate().toString());
    }


    // Test 5: petición a las 21:00 del día 16 del producto 35455 para la compañía 1 (ZARA)
    @Test
    void testNightDay16(){
        ResponseEntity<List<ProductDto>> productResultEntity;
        List<ProductDto> productResult;
        LocalDateTime applicationDateTime = LocalDateTime.parse("2020/06/16 21:00", dateTimeFormatter);

        productResultEntity = productController.getProducts(applicationDateTime, 1, 35455, false);
        productResult = productResultEntity.getBody();

        Assertions.assertNotNull(productResult);
        Assertions.assertEquals(2, productResult.size());

        ProductDto productDto = productResult.get(1);
        Assertions.assertEquals(1, productDto.getCompanyId());
        Assertions.assertEquals(35455, productDto.getProductId());
        Assertions.assertEquals("2020-06-15T16:00", productDto.getStartDate().toString());
    }


    // Test 6: petición a las 21:00 del día 16 del año siguiente del producto 35455 para la compañía 1 (ZARA)
    @Test
    void testNightDay16Year2021NotFound(){
        ResponseEntity<List<ProductDto>> productResultEntity;
        LocalDateTime applicationDateTime = LocalDateTime.parse("2021/06/16 21:00", dateTimeFormatter);

        productResultEntity = productController.getProducts(applicationDateTime, 1, 35455, false);

        Assertions.assertFalse(productResultEntity.hasBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, productResultEntity.getStatusCode());
    }


    @Test
    void testNightDay16Year2020NoCompanyId(){
        ResponseEntity<List<ProductDto>> productResultEntity;
        LocalDateTime applicationDateTime = LocalDateTime.parse("2020/06/16 21:00", dateTimeFormatter);

        productResultEntity = productController.getProducts(applicationDateTime, null, 35455, false);

        Assertions.assertFalse(productResultEntity.hasBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, productResultEntity.getStatusCode());
    }


    @Test
    void testAfternoonDay14Priority(){
        ResponseEntity<List<ProductDto>> productResultEntity;
        List<ProductDto> productResult;
        LocalDateTime applicationDateTime = LocalDateTime.parse("2020/06/14 16:00", dateTimeFormatter);

        productResultEntity = productController.getProducts(applicationDateTime, 1, 35455, true);
        productResult = productResultEntity.getBody();

        Assertions.assertNotNull(productResult);
        Assertions.assertEquals(1, productResult.size());

        ProductDto productDto = productResult.get(0);
        Assertions.assertEquals(1, productDto.getCompanyId());
        Assertions.assertEquals(35455, productDto.getProductId());
        Assertions.assertEquals("2020-06-14T15:00", productDto.getStartDate().toString());
        Assertions.assertEquals(0, productDto.getPriority());
    }


    @Test
    void testInsertProduct() {
        ResponseEntity<ProductDto> productResultEntity;

        ProductDto newProduct = new ProductDto();
        newProduct.setCompanyId(2);
        newProduct.setProductId(5555);
        newProduct.setCurrency("USD");
        newProduct.setPrice(23.5F);
        newProduct.setPriority(1);
        newProduct.setStartDate(LocalDateTime.parse("2022-01-14T15:00"));
        newProduct.setEndDate(LocalDateTime.parse("2022-02-14T15:00"));

        productResultEntity = productController.createProduct(newProduct);
        Assertions.assertEquals(HttpStatus.OK,productResultEntity.getStatusCode());
    }
}
