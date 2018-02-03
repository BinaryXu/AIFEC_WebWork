package com.miner.sell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.miner.sell.dataobject.mapper")
public class SellApplication {

	public static void main(String[] args) {

		SpringApplication.run(SellApplication.class, args);
	}
}
