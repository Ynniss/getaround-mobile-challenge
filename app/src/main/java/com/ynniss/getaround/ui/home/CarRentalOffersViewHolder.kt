package com.ynniss.getaround.ui.home

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ynniss.getaround.R
import com.ynniss.getaround.data.remote.models.CarRentalOffer

class CarRentalOffersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val carName: TextView =
        itemView.findViewById(R.id.text_car_name)

    private val carRentingPrice: TextView =
        itemView.findViewById(R.id.text_car_renting_price)

    private val carRatingCount: TextView =
        itemView.findViewById(R.id.text_car_rating_count)

    private val carRating: RatingBar =
        itemView.findViewById(R.id.ratingbar_car)

    private val carImage: ImageView =
        itemView.findViewById(R.id.image_car)

    fun bind(carRentalOffer: CarRentalOffer) {
        carName.text = String.format(
            itemView.context.getString(
                R.string.car_rental_offer_name
            ), carRentalOffer.brand, carRentalOffer.model
        )

        carRentingPrice.text = String.format(
            itemView.context.getString(
                R.string.car_rental_offer_price_per_day
            ), carRentalOffer.pricePerDay.toString()
        )

        carRating.rating = carRentalOffer.rating.average.toFloat()
        carRatingCount.text = carRentalOffer.rating.count.toString()

        Glide.with(itemView.context)
            .load(carRentalOffer.pictureUrl)
            .placeholder(R.drawable.logo_getaround)
            .into(carImage)
    }
}