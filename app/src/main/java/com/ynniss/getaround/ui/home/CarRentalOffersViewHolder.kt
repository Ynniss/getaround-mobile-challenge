package com.ynniss.getaround.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ynniss.getaround.R
import com.ynniss.getaround.data.remote.models.CarRentalOffer
import com.ynniss.getaround.databinding.CardCarItemBinding

class CarRentalOffersViewHolder(
    private val itemViewBinding: CardCarItemBinding,
    private val onRentalOfferClick: (Int) -> Unit
) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    init {
        itemView.setOnClickListener {
            onRentalOfferClick(adapterPosition)
        }
    }

    fun bind(carRentalOffer: CarRentalOffer) {
        itemViewBinding.apply {
            textCarName.text = String.format(
                itemView.context.getString(
                    R.string.car_rental_offer_name
                ), carRentalOffer.brand, carRentalOffer.model
            )

            textCarRentingPrice.text = String.format(
                itemView.context.getString(
                    R.string.car_rental_offer_price_per_day
                ), carRentalOffer.pricePerDay.toString()
            )

            ratingbarCar.rating = carRentalOffer.rating.average.toFloat()
            textCarRatingCount.text = carRentalOffer.rating.count.toString()

            Glide.with(itemView.context)
                .load(carRentalOffer.pictureUrl)
                .placeholder(R.drawable.no_image)
                .into(imageCar)
        }
    }
}