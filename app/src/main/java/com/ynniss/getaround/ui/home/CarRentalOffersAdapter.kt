package com.ynniss.getaround.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ynniss.getaround.R
import com.ynniss.getaround.data.remote.models.CarRentalOffer

class CarRentalOffersAdapter : RecyclerView.Adapter<CarRentalOffersViewHolder>() {
    private var carRentalOffersData: List<CarRentalOffer>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarRentalOffersViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.card_car_item, parent, false)

        return CarRentalOffersViewHolder(view)
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
