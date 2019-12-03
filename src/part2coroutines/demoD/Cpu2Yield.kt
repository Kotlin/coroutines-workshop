package part2coroutines.demoD

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    withTimeoutOrNull(1300) {
        repeat(1000) { i ->
            println("I'm working $i ...")
            doSomethingSlow()
            yield()
        }
    }
}