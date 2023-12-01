package kotlinl.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.slf4j.LoggerFactory


/**
 * @author violet
 * @since 2023/11/24
 */
fun main() = runBlocking {
    val log = LoggerFactory.getLogger("coroutine_lock")
    var mutex = Mutex()
    var i = 0
    while (i < 100) {
        launch(Dispatchers.IO) {
            mutex.withLock {
                try {
                    log.info("lock coroutine status $mutex")
                    delay(1000)
                } finally {
                    mutex = Mutex()
                    log.info("release lock $mutex")
                }
            }
        }
        i++
    }
}