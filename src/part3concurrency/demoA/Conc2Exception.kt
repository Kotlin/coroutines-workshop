package part3concurrency.demoA

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    launch {
        launch {
            delay(500)
            error("job1: something went wrong")
        }
        launch {
            delay(1000)
            println("job2: done")
        }
        delay(2000)
    }
}