package com.example.myyyyapplication.presentation

import java.io.Serializable

sealed class GroupType: Serializable {
    data object Interests: GroupType()
    data object Prices: GroupType()
    data object Days: GroupType()
    data object Hours: GroupType()
}