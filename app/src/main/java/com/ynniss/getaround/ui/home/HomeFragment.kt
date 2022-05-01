package com.ynniss.getaround.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
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
        val adapter = CarRentalOffersAdapter()
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