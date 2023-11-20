package application.config.init

import application.model.dto.BeanLifeCycle
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean

class BeanInitializer(
    private val bean: BeanLifeCycle,
) : InitializingBean, DisposableBean {
    private val log = LoggerFactory.getLogger(BeanInitializer::class.java)
    override fun afterPropertiesSet() {
        log.info("--bean initializer--")
        log.info("--bean message: ${bean.message}--")
    }

    override fun destroy() {
        log.info("--bean destroy--")
    }

}