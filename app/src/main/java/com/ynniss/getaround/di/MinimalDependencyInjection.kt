package com.ynniss.getaround.di

import com.ynniss.getaround.data.remote.CarsApi
import com.ynniss.getaround.data.repositories.CarRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MinimalDependencyInjection {
    private const val BASE_TIMEOUT = 30L
    private const val CARS_API_BASE_URL =
        "https://raw.githubusercontent.com/drivy/jobs/master/mobile/api/"

    fun provideCarsApi(): CarsApi {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(BASE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(BASE_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(BASE_TIMEOUT, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(CARS_API_BASE_URL)
            .client(okHttpClient)
            .build()
            .create(CarsApi::class.java)
    }

    // Should be injected too
    // fun provideCarRepository(): CarRepository {
    //    return CarRepository(provideCarsApi())
    //}
}