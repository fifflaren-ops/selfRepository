package cn.jj.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

//MyBatis的配置，把cn.jj.mapper下面的所有mapper注册成bean
@Configuration
@MapperScan(basePackages = "cn.jj.mapper")
public class MybatisConfiguration {

}
