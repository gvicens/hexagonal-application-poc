package org.gvm.hexagonal.testproject.domain;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Product {

    private Integer id;

    private Integer companyId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer productId;

    private Integer priority;

    private Float price;

    private String currency;
}
