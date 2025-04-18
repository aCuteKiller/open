package bai.demo.searchThink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"bai.demo.**"})
public class SearchThinkDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchThinkDemoApplication.class, args);
    }

}
