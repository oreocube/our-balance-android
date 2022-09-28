package com.ourbalance.data.api

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

class ResponseUnboxingInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val responseJson = response.extractResponseJson()
        val payload = if (responseJson.has(KEY_DATA)) responseJson[KEY_DATA] else EMPTY_JSON

        return response.newBuilder()
            .message(responseJson[KEY_ERROR_MESSAGE].toString())
            .body(payload.toString().toResponseBody())
            .build()
    }

    private fun Response.extractResponseJson(): JSONObject {
        val jsonString = this.body?.string() ?: EMPTY_JSON
        return try {
            JSONObject(jsonString)
        } catch (e: Throwable) {
            Timber.d("서버 응답 오류 : $jsonString")
            throw Throwable(message = e.message)
        }
    }

    companion object {
        const val EMPTY_JSON = "{}"
        const val KEY_DATA = "data"
        const val KEY_ERROR_MESSAGE = "error"
    }
}
