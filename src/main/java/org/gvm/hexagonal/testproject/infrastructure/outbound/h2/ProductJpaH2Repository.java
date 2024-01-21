package org.gvm.hexagonal.testproject.infrastructure.outbound.h2;

import org.gvm.hexagonal.testproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductJpaH2Repository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "select * from PRODUCTS " +
            "        where company_id = :companyid " +
            "          and product_id = :productid " +
            "          and :applicationdatetime between start_date and end_date",
           nativeQuery = true)
    List<ProductEntity> getProducts(
            @Param("applicationdatetime") LocalDateTime applicationDateTime,
            @Param("companyid") Integer companyId,
            @Param("productid") Integer productId);


    @Query(value = "select * from (" +
            "                       select * from PRODUCTS " +
            "                        where company_id = :companyid " +
            "                          and product_id = :productid " +
            "                          and :applicationdatetime between start_date and end_date" +
            "                        order by priority" +
            "                     )" +
            "        where rownum = 1",
            nativeQuery = true)
    List<ProductEntity> getPriorityProduct(
            @Param("applicationdatetime") LocalDateTime applicationDateTime,
            @Param("companyid") Integer companyId,
            @Param("productid") Integer productId);

    @Modifying
    @Query(value = "insert into PRODUCTS(company_id, start_date, end_date, product_id, priority, price, currency) values (:company_id, :start_date, :end_date, :product_id, :priority, :price, :currency)",
           nativeQuery = true)
    void createProduct(Product product);

}
