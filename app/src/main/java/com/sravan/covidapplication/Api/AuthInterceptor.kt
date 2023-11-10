package com.sravan.covidapplication.Api

import com.sravan.covidapplication.Utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    @Inject
    lateinit var tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        val token = tokenManager.getToken()
        request.addHeader("Authorization", "fsq3UljhIwcI7RHKMuQagTCBNUNQJ6n1r3wjCfHDLv1hqJw=")
        request.addHeader("accept", "application/json")
        return chain.proceed(request.build())
    }
}