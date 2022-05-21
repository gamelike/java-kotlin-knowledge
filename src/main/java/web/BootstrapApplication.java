package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author violet.
 */
@EnableJpaRepositories(basePackages = "application.infrastructure")
@EntityScan(basePackages = "application.model.po")
@SpringBootApplication
@ComponentScan(basePackages = {"application"})
public class BootstrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }
}
