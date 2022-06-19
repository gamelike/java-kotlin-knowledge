package kotlinl.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * My first coroutine
 */
fun main() = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    launch { helloWorld() }
    println("Hello") // main coroutine
}

/**
 * first suspend function.
 */
suspend fun helloWorld() {
    delay(1000L)
    println("Hello World!")
}