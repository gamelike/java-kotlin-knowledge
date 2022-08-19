package application.config.init;

import application.rest.PensieveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author violet
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class Test2 extends AbstractTest {

    private final PensieveService pensieveService;

    @Override
    void println() {
//        String database = pensieveService.getDataBaseMessage("DATABASE_ES", null);
//        log.info(database);
    }
}
