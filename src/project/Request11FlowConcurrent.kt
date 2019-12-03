package project

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun loadContributorsFlowConcurrent(req: RequestData) : Flow<List<User>> = flow {
    val service = createGitHubServiceCoroutine(req.username, req.password)
    log.info("Loading ${req.org} repos")
    val repos = service.listOrgRepos(req.org).await()
    log.info("${req.org}: loaded ${repos.size} repos")
    // define resulting flow
    val resultFlow = repos.asFlow()
        .concurrentMapMerge(4) { repo ->
            val users = service.listRepoContributors(req.org, repo.name).await()
            log.info("${repo.name}: loaded ${users.size} contributors")
            users
        }
        .scan(emptyList<User>()) { contribs, users ->
            (contribs + users).aggregateSlow()
        }
    // emit all results
    emitAll(resultFlow)
}

suspend fun createGitHubServiceCoroutine(username: String, password: String): GitHubService =
    withContext(Dispatchers.IO) {
        createGitHubService(username, password)
    }

fun <T, R> Flow<T>.concurrentMapMerge(concurrency: Int, block: suspend (T) -> R): Flow<R> =
    flatMapMerge(concurrency) { value ->
        flow { emit(block(value)) }
    }
