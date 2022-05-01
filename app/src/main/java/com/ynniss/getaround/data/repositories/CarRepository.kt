package com.ynniss.getaround.data.repositories

import com.ynniss.getaround.data.remote.CarsApi
import com.ynniss.getaround.data.remote.models.CarRentalOffer
import com.ynniss.getaround.di.MinimalDependencyInjection
import com.ynniss.getaround.utils.ApiResponseWrapper


class CarRepository(
    private val api: CarsApi = MinimalDependencyInjection.provideCarsApi()
) {
    suspend fun getCarRentalOffers(): ApiResponseWrapper<List<CarRentalOffer>> {
        return try {
            val apiResponse = api.getCarRentalOffers()

            if (apiResponse.isSuccessful && apiResponse.body() != null) {
                ApiResponseWrapper.Success(apiResponse.body()!!)
            } else {
                ApiResponseWrapper.Error("An error occurred while retrieving car rental offers.")
            }
        } catch (e: Exception) {
            ApiResponseWrapper.Error(
                "An Error occurred. Is your device connected to internet ?"
            )
        }
    }
}