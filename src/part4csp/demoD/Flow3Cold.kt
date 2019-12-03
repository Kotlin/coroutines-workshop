package part4csp.demoD

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

private fun producer(): Flow<Int> =
    flow {
        for (x in 1..5) {
            emit(x * x)
        }
    }

fun main() = runBlocking<Unit> {
    val flow = producer()
    // flow.collect { element -> println(element) }
    println("Done!")
}
