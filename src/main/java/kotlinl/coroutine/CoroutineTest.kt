package kotlinl.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


fun main() = runBlocking(Dispatchers.IO) {
    var i = 1
    val startTime = System.currentTimeMillis()
    val lock = Mutex()
    while (i < 100_000) {
        lock.withLock {
            i++
        }
    }
    println(i)
    val endTime = System.currentTimeMillis()
    println("need cost time: ${(endTime - startTime)}ms")
}