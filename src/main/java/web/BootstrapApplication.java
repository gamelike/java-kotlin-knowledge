package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author violet.
 */
@EnableJpaRepositories(basePackages = {"application.infrastructure", "**.model.repository"})
@EntityScan(basePackages = {"application.model.po", "web.**.po"})
//@EnableFeignClients(basePackages = "application.rest")
@SpringBootApplication
@ComponentScan(basePackages = {"application", "web"})
public class BootstrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }
}
