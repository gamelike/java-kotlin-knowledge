package kotlinl.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import kotlin.random.Random
import kotlin.time.Duration


/**
 * @author violet
 * @since 2023/11/21
 */
fun main() {
    val log = LoggerFactory.getLogger("coroutine")
    CoroutineScope(Dispatchers.IO).launch {
        while (true) {
            val random = Random.Default
            val id = random.nextInt(100).toString()
            val lock = Lock.idLock(id)
            lock.withLock {
                log.info("---handler $id processing---")
                delay(Duration.parse("10s"))
                log.info("---handler $id completed---")
            }
        }
    }
    while(true) {}
}

object Lock {
    val map = ConcurrentHashMap<String, Mutex>()
    fun idLock(id: String): Mutex {
        synchronized(Lock) {
            return if (map[id] == null) {
                map[id] = Mutex()
                map[id]!!
            } else {
                map[id]!!
            }
        }
    }

    fun remove(id: String) {
        map.remove(id)
    }
}