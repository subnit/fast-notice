package fastnotice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.subnit"})
@MapperScan("com.subnit.dao")
public class FastNoticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastNoticeApplication.class, args);
	}


}
