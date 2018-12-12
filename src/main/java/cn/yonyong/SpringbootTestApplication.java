package cn.yonyong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.yonyong.dao")
public class SpringbootTestApplication {

    public static void main(String... args) {
        SpringApplication.run(SpringbootTestApplication.class, args);
    }
}
