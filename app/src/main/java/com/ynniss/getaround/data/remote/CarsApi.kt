package com.ynniss.getaround.data.remote

import com.ynniss.getaround.data.remote.models.CarRentalOffer
import retrofit2.Response
import retrofit2.http.*


interface CarsApi {
    @GET("cars.json")
    suspend fun getCarRentalOffers(
    ): Response<List<CarRentalOffer>>
}