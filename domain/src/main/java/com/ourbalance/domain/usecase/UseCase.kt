package com.ourbalance.domain.usecase

import com.ourbalance.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        } catch (e: Throwable) {
            Result.Error(e.message ?: "Unknown Error")
        }
    }

    protected abstract suspend fun execute(parameters: P): R
}
