package com.ynniss.getaround.ui.show_car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ynniss.getaround.R
import com.ynniss.getaround.data.remote.models.CarRentalOffer
import com.ynniss.getaround.databinding.FragmentShowCarBinding

class ShowCarFragment : Fragment() {
    private var _binding: FragmentShowCarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowCarBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateScreen()
    }

    private fun populateScreen() {
        val carRentalOffer: CarRentalOffer? = arguments?.getParcelable("carRentalOffer")

        carRentalOffer?.let {
            binding.apply {
                // Car Rental Offer
                textCarName.text = String.format(
                    requireContext().getString(
                        R.string.car_rental_offer_name
                    ), carRentalOffer.brand, carRentalOffer.model
                )
                textCarRentingPrice.text = String.format(
                    requireContext().getString(
                        R.string.car_rental_offer_price_per_day
                    ), carRentalOffer.pricePerDay.toString()
                )
                ratingbarCar.rating = carRentalOffer.rating.average.toFloat()
                textCarRatingCount.text = carRentalOffer.rating.count.toString()

                Glide.with(requireContext())
                    .load(carRentalOffer.pictureUrl)
                    .placeholder(R.drawable.no_image)
                    .into(imageCar)

                // Owner
                Glide.with(requireContext())
                    .load(carRentalOffer.owner.pictureUrl)
                    .placeholder(R.drawable.no_image)
                    .into(imageCarOwner)

                textCarOwnerRatingCount.text = carRentalOffer.owner.rating.count.toString()
                textCarOwnerName.text = carRentalOffer.owner.name
                ratingbarCarOwner.rating = carRentalOffer.owner.rating.average.toFloat()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}