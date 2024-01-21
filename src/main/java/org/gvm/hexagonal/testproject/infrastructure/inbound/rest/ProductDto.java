package org.gvm.hexagonal.testproject.infrastructure.inbound.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Integer id;

    private Integer companyId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer productId;

    private Integer priority;

    private Float price;

    private String currency;

}
