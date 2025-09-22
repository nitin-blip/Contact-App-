package com.example.contactapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contactapp.data.DataBase.DataBaseInit
import com.example.contactapp.data.repository.ContactRepository
import com.example.contactapp.presentation.contactViewModel
import com.example.contactapp.presentation.navigation.Appnavigation.AppNavigation
import com.example.contactapp.ui.theme.ContactAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val  repository = ContactRepository(DataBaseInit.getDatabase(application).dao)
            val viewModel = viewModel {
                contactViewModel(repository)
            }
                ContactAppTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box (modifier = Modifier.padding(innerPadding)){
                            AppNavigation(viewModel = viewModel)

                        }
                    }
                }
            }
        }
    }

