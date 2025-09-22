package com.example.contactapp.presentation.navigation.routes

import kotlinx.serialization.Serializable

sealed class Routes{


    @Serializable
    object  HomeScreen : Routes()

    @Serializable
    data class  AddEditScreen(val contactInt: Int?) : Routes()
}