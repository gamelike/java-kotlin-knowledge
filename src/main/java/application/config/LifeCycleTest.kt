package application.config

import application.config.init.BeanInitializer
import application.model.dto.BeanLifeCycle
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class LifeCycleTest {
    @Bean(destroyMethod = "destroy")
    open fun lifeCycle() =
        BeanInitializer(BeanLifeCycle("lifecycle test"))
}