package com.wms.warehouseMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.wms" })
@EnableJpaRepositories(basePackages = { "com.wms.repository" })
@EntityScan(basePackages = { "com.wms.entity" })
public class WarehouseMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseMsApplication.class, args);
	}

}
