package com.example.myyyyapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myyyyapplication.data.repository.WorkshopRepository
import com.example.myyyyapplication.data.source.remote.model.WorkshopModel
import com.example.myyyyapplication.presentation.GroupType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GroupedClubsViewModel(
    val groupType: GroupType,
    private val workshopRepository: WorkshopRepository,
): ViewModel() {

    private val _workshopsLiveData: MutableLiveData<Map<String, List<WorkshopModel>>> = MutableLiveData()
    val workshopsLiveData: LiveData<Map<String, List<WorkshopModel>>> = _workshopsLiveData

    init {
        viewModelScope.launch {
            _workshopsLiveData.value = workshopRepository.getWorkshops()
                .groupBy {
                    when(groupType) {
                        GroupType.Days -> it.days
                        GroupType.Hours -> it.hours
                        GroupType.Interests -> it.interests
                        GroupType.Prices -> it.price
                    }
                }
        }
    }

    fun updateWorkshop(workshopModel: WorkshopModel) {
        viewModelScope.launch {
            workshopRepository.updateWorkshop(workshopModel)
        }
    }

}