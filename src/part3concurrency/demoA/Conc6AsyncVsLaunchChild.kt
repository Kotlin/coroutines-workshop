package part3concurrency.demoA

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val job = launch {
        delay(1000)
        println("launch: done")
        error("launch: something went wrong")
    }
    val deferred = async {
        delay(500)
        println("async: done")
        error("async: something went wrong")
    }
}