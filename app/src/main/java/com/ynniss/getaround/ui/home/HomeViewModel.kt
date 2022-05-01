package com.ynniss.getaround.ui.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.ynniss.getaround.data.remote.models.CarRentalOffer
import com.ynniss.getaround.data.repositories.CarRepository
import com.ynniss.getaround.utils.ApiResponseWrapper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val LIVEDATA_TAG = "_carRentalOffersResponse"
    }

    private val _carRentalOffersResponse: MutableLiveData<ApiResponseWrapper<List<CarRentalOffer>>> =
        savedStateHandle.getLiveData(LIVEDATA_TAG)

    val carRentalOffersResponse: LiveData<ApiResponseWrapper<List<CarRentalOffer>>> =
        _carRentalOffersResponse

    init {
        if (_carRentalOffersResponse.value == null) {
            getCarRentalOffers()
        }
    }

    // CarRepository should ideally be injected, instantiation is currently handled by ViewModel..
    // It would then require using AbstractSavedStateViewModelFactory, making the whole thing more complicated..
    private fun getCarRentalOffers() {
        viewModelScope.launch(IO) {
            val response = CarRepository().getCarRentalOffers()

            withContext(Main) {
                _carRentalOffersResponse.value = response
                savedStateHandle.set(LIVEDATA_TAG, _carRentalOffersResponse.value)
            }
        }
    }
}