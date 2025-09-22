package com.example.contactapp.presentation.navigation.Appnavigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.contactapp.presentation.contactViewModel
import com.example.contactapp.presentation.navigation.routes.Routes
import com.example.contactapp.presentation.screens.AddEditScreenUI
import com.example.contactapp.presentation.screens.HomeScreenUi


@Composable
fun AppNavigation(modifier: Modifier = Modifier , viewModel: contactViewModel) {

    val  navController = rememberNavController()
    NavHost (navController = navController, startDestination = Routes.HomeScreen){
       composable <Routes.HomeScreen>{
           HomeScreenUi(viewModel = viewModel,navController =navController)


       }
        composable <Routes.AddEditScreen>{
            val  addEditScreenParams = it.toRoute<Routes.AddEditScreen>()
            AddEditScreenUI(viewModel = viewModel, navController = navController,contactId = addEditScreenParams.contactInt)


        }


     }

}