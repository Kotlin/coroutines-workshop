package part4csp.demoD

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking<Unit> {
    val flow = (1..5).asFlow()
    flow
        .map { x -> x * x }
        .collect { element -> println(element) }
    println("Done!")
}
