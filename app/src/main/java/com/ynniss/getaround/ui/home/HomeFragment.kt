package com.ynniss.getaround.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ynniss.getaround.R
import com.ynniss.getaround.databinding.FragmentHomeBinding
import com.ynniss.getaround.utils.ApiResponseWrapper


class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CarRentalOffersAdapter { carRentalOffer ->
            val bundle = bundleOf(Pair("carRentalOffer", carRentalOffer))
            findNavController().navigate(R.id.action_homeFragment_to_showCarFragment, bundle)
        }

        binding.recyclerCarRentalOffers.adapter = adapter

        homeViewModel.carRentalOffersResponse.observe(viewLifecycleOwner) { carRentalOffersResponse ->
            when (carRentalOffersResponse) {
                is ApiResponseWrapper.Success ->
                    adapter.setCarRentalOffersData(
                        carRentalOffersResponse.apiResponse!!
                    )
                else ->
                    Snackbar.make(
                        binding.root,
                        carRentalOffersResponse.errorMessage!!,
                        Snackbar.LENGTH_SHORT
                    ).show()
            }
            binding.progressIndeterminate.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerCarRentalOffers.adapter = null
        _binding = null
    }
}