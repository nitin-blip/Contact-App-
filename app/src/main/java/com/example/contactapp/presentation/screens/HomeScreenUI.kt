package com.example.contactapp.presentation.screens


import android.R
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle



import androidx.navigation.NavController
import com.example.contactapp.data.entities.Contact
import com.example.contactapp.presentation.AppState
import com.example.contactapp.presentation.contactViewModel
import com.example.contactapp.presentation.navigation.routes.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUi(modifier: Modifier = Modifier,viewModel: contactViewModel,navController: NavController) {
    var  state = viewModel.state.collectAsStateWithLifecycle()
    when(state.value){
        is AppState.Data -> {
            Scaffold(

                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFF886316), // Blue background
                            titleContentColor = Color.Black      // Title text color
                        ),
                            title = {Text(text = "Contact App")
                            },
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                Icon(Icons.Filled.ArrowBack, "backIcon")


                            }
                        }
                        )



                },

                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        navController.navigate(Routes.AddEditScreen(null))

                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
    }
            )


            {
                val  contacts =( state.value as  AppState.Data ).data
                LazyColumn(modifier =modifier
                    .fillMaxSize()
                    .padding(it)) {
                    items (contacts){
                        ContactItemUI(contact = it,viewModel = viewModel,navController =navController)
                    }
                }

            }
        }
        is AppState.Loading -> {
            Box (contentAlignment = Alignment.Center,modifier = modifier.fillMaxSize()){
                CircularProgressIndicator(
                    color = Color.Blue
                )
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ContactItemUI(contact: Contact, viewModel: contactViewModel,navController: NavController) {


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
                .combinedClickable(
                    onClick = {},
                    onLongClick = {
                        navController.navigate(Routes.AddEditScreen(contact.id))
                    },
                    onDoubleClick = {}
                ),


        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = contact.name)
                    Text(text = contact.phoneNo)
                    Text(text = contact.email)

                }


                Icon(
                    imageVector = Icons.Default.Delete, contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            viewModel.deleteContact(contact)


                        })
            }
        }



    }



