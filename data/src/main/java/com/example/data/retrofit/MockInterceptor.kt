package com.example.data.retrofit

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor(private val context: Context)
     : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            try {
                val response = chain.proceed(originalRequest)
                if (response.isSuccessful) {
                    return response
                }
            } catch (e: Exception) {
                Log.e("MockResponseInterceptor", "Request failed: ${e.message}")
            }

            val mockJsonFileName = "courses.json"
            val mockJson = context.assets.open(mockJsonFileName).bufferedReader().use { it.readText() }

            return Response.Builder()
                .request(originalRequest)
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("Mock Response")
                .body(mockJson.toResponseBody("application/json".toMediaTypeOrNull()))
                .build()
        }
    }