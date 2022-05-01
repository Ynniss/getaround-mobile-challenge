package com.ynniss.getaround.data.remote.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarRentalOffer(
    val brand: String,
    val model: String,
    val owner: Owner,
    @SerializedName("picture_url")
    val pictureUrl: String,
    @SerializedName("price_per_day")
    val pricePerDay: Int,
    val rating: Rating
) : Parcelable

@Parcelize
data class Owner(
    val name: String,
    @SerializedName("picture_url")
    val pictureUrl: String,
    val rating: Rating
) : Parcelable

@Parcelize
data class Rating(
    val average: Double,
    val count: Int
) : Parcelable