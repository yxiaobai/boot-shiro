package com.spbs.bootshiro;

import com.spbs.bootshiro.common.config.FebsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@MapperScan("com.spbs.bootshiro.*.dao")
@EnableCaching
@EnableTransactionManagement
@EnableConfigurationProperties({FebsProperties.class})
@EnableAsync
public class BootshiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootshiroApplication.class, args);
	}

}
