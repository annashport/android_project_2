package com.example.myyyyapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myyyyapplication.data.repository.WorkshopRepository
import com.example.myyyyapplication.data.source.remote.model.WorkshopModel
import kotlinx.coroutines.launch

class PlanViewModel(
    private val workshopRepository: WorkshopRepository,
    ): ViewModel() {

    private val _likedWorkshopsLiveData: MutableLiveData<List<WorkshopModel>> = MutableLiveData()
    val likedWorkshopsLiveData: LiveData<List<WorkshopModel>> = _likedWorkshopsLiveData

    init {
        reload()
    }

    fun reload() {
        viewModelScope.launch {
            _likedWorkshopsLiveData.value = workshopRepository.getLikedWorkshops()
        }
    }

    fun updateWorkshop(workshopModel: WorkshopModel) {
        viewModelScope.launch {
            workshopRepository.updateWorkshop(workshopModel)
        }
    }
}