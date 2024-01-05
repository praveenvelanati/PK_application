//package com.sravan.covidapplication.Network
//
//import com.sravan.covidapplication.Api.TownsApi
//import com.sravan.covidapplication.Utils.Constants
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.Retrofit.Builder
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton
//
//@InstallIn(SingletonComponent::class)
//@Module
//class NetworkClass {
//
//    @Singleton
//    @Provides
//    fun providesInterceptor(): Interceptor{
//
//        return Interceptor{
//
//            val request = it.request().newBuilder()
//            request.addHeader("auth-token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJqb2huIiwiZW1haWxfaWQiOiJqb2huQHRlc3QuY29tIiwiaWF0IjoxNjk5OTMyMTA1LCJleHAiOjE3MDI1MjQxMDV9.D1Z3TAPHMFs3kbEHzRqGs4PaXR4lSLN6oKvEc2qhVmM")
//            val actualRequest = request.build()
//            it.proceed(actualRequest)
//        }
//    }
//
//    @Singleton
//    @Provides
//    fun providesOkhttpClient(interceptor: Interceptor): OkHttpClient{
//
//        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
//    }
//
//    @Singleton
//    @Provides
//    fun providesRetrofit(): Retrofit.Builder{
//
//        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create())
//    }
//
//    @Singleton
//    @Provides
//    fun providesTownsApi(retrofitBuilder: Builder, okHttpClient: OkHttpClient): TownsApi{
//
//        return retrofitBuilder.client(okHttpClient).build().create(TownsApi::class.java)
//    }
//}