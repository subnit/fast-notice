package com.subnit.notice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.subnit.notice.data"})
@MapperScan("com.subnit.notice.data.dao")
public class FastNoticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastNoticeApplication.class, args);
	}


}
