package com.ynniss.getaround.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ynniss.getaround.data.remote.models.CarRentalOffer
import com.ynniss.getaround.databinding.CardCarItemBinding

class CarRentalOffersAdapter(private val onRentalOfferClick: (CarRentalOffer) -> Unit) :
    RecyclerView.Adapter<CarRentalOffersViewHolder>() {
    private var carRentalOffersData: List<CarRentalOffer>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarRentalOffersViewHolder {
        val itemViewBinding =
            CardCarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CarRentalOffersViewHolder(itemViewBinding) {
            onRentalOfferClick(carRentalOffersData!![it])
        }
    }

    override fun onBindViewHolder(holder: CarRentalOffersViewHolder, position: Int) {
        holder.bind(carRentalOffersData!![position])
    }

    override fun getItemCount(): Int {
        return if (carRentalOffersData == null) 0 else carRentalOffersData!!.size
    }

    fun setCarRentalOffersData(newCarRentalOffersListing: List<CarRentalOffer>) {
        this.carRentalOffersData = newCarRentalOffersListing
        notifyDataSetChanged()
    }
}
