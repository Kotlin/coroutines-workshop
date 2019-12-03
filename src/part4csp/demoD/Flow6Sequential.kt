package part4csp.demoD

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.*

private fun producer(): Flow<Int> =
    flow {
        for (x in 1..5) {
            delay(500)
            emit(x * x)
        }
    }

fun main() = runBlocking<Unit> {
    val flow = producer()
    val time = measureTimeMillis {
        flow.collect { element ->
            println(element)
        }
    }
    println("Done in $time ms")
}
