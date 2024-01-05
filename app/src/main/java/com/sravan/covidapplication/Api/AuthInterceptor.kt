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
        request.addHeader("auth-token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJqb2huIiwiZW1haWxfaWQiOiJqb2huQHRlc3QuY29tIiwiaWF0IjoxNjk5OTMyMTA1LCJleHAiOjE3MDI1MjQxMDV9.D1Z3TAPHMFs3kbEHzRqGs4PaXR4lSLN6oKvEc2qhVmM")
//        request.addHeader("accept", "application/json")
        return chain.proceed(request.build())
    }
}