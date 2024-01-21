package org.gvm.hexagonal.testproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.gvm.hexagonal.testproject"})
public class HexagonalApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(HexagonalApp.class, args);
    }
}
