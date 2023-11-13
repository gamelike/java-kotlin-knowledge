package application.config.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author violet
 */
@Component
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractTest {

    @PostConstruct
    public void print() {
        println();
    }

    abstract void println();

}
