package web.annotation;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import web.BootstrapApplication;

import java.lang.annotation.*;

/**
 * @author violet.
 */
@SpringBootTest(classes = BootstrapApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface BaseTest {
}
