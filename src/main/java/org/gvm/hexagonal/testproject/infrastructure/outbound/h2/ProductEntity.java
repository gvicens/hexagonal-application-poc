package org.gvm.hexagonal.testproject.infrastructure.outbound.h2;



import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="products")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name="company_id", nullable = false)
    private Integer companyId;

    @Column(name="start_date")
    private LocalDateTime startDate;

    @Column(name="end_date")
    private LocalDateTime endDate;

    @Column(name="product_id")
    private Integer productId;

    @Column(name="priority")
    private Integer priority;

    @Column(name="price")
    private Float price;

    @Column(name="currency")
    private String currency;
}
