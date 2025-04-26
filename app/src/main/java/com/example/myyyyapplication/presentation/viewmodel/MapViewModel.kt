package com.example.myyyyapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myyyyapplication.data.repository.WorkshopRepository
import com.example.myyyyapplication.data.source.remote.model.WorkshopModel
import kotlinx.coroutines.launch

class MapViewModel(private val workshopRepository: WorkshopRepository,): ViewModel() {
    private val _workshopsLiveData: MutableLiveData<List<WorkshopModel>> = MutableLiveData()
    val workshopsLiveData: LiveData<List<WorkshopModel>> = _workshopsLiveData

    init {
        viewModelScope.launch {
            _workshopsLiveData.value = workshopRepository.getWorkshops()
        }
    }
}