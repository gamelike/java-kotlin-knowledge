package application.rest.fallback;

import application.rest.PensieveService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author violet
 */
@Slf4j
//@Service
public class PensieveFallback implements PensieveService {
    @Override
    public String getDataBaseMessage(String databaseType, String typesAliasName) {
        log.info("熔断触发");
        return null;
    }
}
