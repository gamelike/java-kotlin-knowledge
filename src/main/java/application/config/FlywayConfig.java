package application.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

//@Configuration
public class FlywayConfig {

    /**
     * 需要设置flyway.enabled = false.由自己来创建配置并写入.执行
     */
//    @Autowired
//    public FlywayConfig(DataSource dataSource) {
//            Flyway.configure()
//                    .dataSource(dataSource)
//                    .baselineOnMigrate(true)
//                    .table("history")
//                    .load().migrate();
//    }

    /**
     * flyway必须设置 flyway.enabled = true.
     */
    @Bean
    FlywayMigrationInitializer flywayMigrationInitializer(Flyway flyway) {
        return new FlywayMigrationInitializer(flyway, f -> {});
    }


    static class Dummy {

    }

    /**
     *  控制flyway初始顺序，保证jpa首先执行.
     */
    @Bean
    @DependsOn("entityManagerFactory")
    Dummy delayedMigrate(Flyway flyway, FlywayProperties flywayProperties) {
        if (flywayProperties.isEnabled()) flyway.migrate();
        return new Dummy();
    }

}
