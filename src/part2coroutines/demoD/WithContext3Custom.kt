package part2coroutines.demoD

import kotlinx.coroutines.*

private suspend fun doSomething() = withContext(computation) {
    doSomethingSlow()
}

fun main() = runBlocking<Unit> {
    withTimeoutOrNull(1300) {
        for (i in 0 until 1000) {
            println("I'm working $i ...")
            doSomething()
        }
    }
}