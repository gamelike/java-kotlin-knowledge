package application.config.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
