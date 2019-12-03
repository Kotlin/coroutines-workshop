package part4csp.demoD

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.*

private fun square(x: Int): Flow<Int> =
    flow {
        delay(500)
        emit(x * x)
    }

fun main() = runBlocking<Unit> {
    val flow = (1..5).asFlow()
        .flatMapMerge(5) { x -> square(x) }
    
    val time = measureTimeMillis {
        flow.collect { element ->
            println(element)
        }
    }
    println("Done in $time ms")
}
