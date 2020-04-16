package cn.jj.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "cn.jj.mapper")
public class MybatisConfiguration {

}
