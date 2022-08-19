package application.config;

import application.rest.PensieveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author violet
 */
@RequiredArgsConstructor
@Slf4j
@Configuration
public class FeignConfig {

    @Lazy
    private final PensieveService service;

    static class Asset { }

    @Bean
    public Asset asset() {
        String info = service.getDataBaseMessage("DATABASE_ES", null);
        log.info("info: {}", info);
        return new Asset();
    }
}
