package cn.jj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ClinicApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClinicApplication.class, args);
	}
}
