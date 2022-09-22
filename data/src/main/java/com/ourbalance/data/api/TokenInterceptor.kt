package com.ourbalance.data.api

import com.ourbalance.data.source.remote.auth.AuthDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val authDataSource: AuthDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/json")

        runBlocking {
            val token = authDataSource.getToken()
            if (token.isNotEmpty()) {
                request.addHeader("Authorization", "Bearer $token")
            }
        }
        return chain.proceed(request.build())
    }
}
