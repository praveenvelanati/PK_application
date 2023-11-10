package com.sravan.covidapplication.Network

import com.sravan.covidapplication.Api.AuthInterceptor
import com.sravan.covidapplication.Api.CasesApi
import com.sravan.covidapplication.Api.SearchApi
import com.sravan.covidapplication.Api.TestApi
import com.sravan.covidapplication.Utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) 
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun providesCasesApi(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): CasesApi {
        return retrofitBuilder.client(okHttpClient).build().create(CasesApi::class.java)
    }

    @Singleton
    @Provides
    fun providesSearchApi(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient):    SearchApi {
        return retrofitBuilder.client(okHttpClient).build().create(SearchApi::class.java)
    }

    @Singleton
    @Provides
    fun providesTestApi(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): TestApi {
        return retrofitBuilder.client(okHttpClient).build().create(TestApi::class.java)
    }
}