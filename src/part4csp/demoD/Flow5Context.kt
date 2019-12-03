package part4csp.demoD

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.*

private fun producer(): Flow<Int> =
    flow {
        println("Emitting in $coroutineContext")
        for (x in 1..5) {
            emit(x * x)
        }
    }

fun main() = runBlocking<Unit> {
    val flow = producer()
    println("Collecting in $coroutineContext")
    flow.collect { element -> println(element) }
}
