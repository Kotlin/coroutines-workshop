package part4csp.demoD

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking<Unit> {
    val flow = flow {
        for (x in 1..5) {
            emit(x * x)
        }
    }
    flow.collect { element ->
        println(element)
    }
    println("Done!")
}
