package com.example.contactapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

import com.example.contactapp.R
import com.example.contactapp.data.entities.Contact
import com.example.contactapp.presentation.AppState
import com.example.contactapp.presentation.contactViewModel
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreenUI(modifier: Modifier = Modifier,
                    viewModel: contactViewModel ,
                    navController: NavController,
                   contactId:Int?= null
) {
    var name = remember { mutableStateOf("") }
    var number = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    val state = viewModel.state.collectAsStateWithLifecycle()

    var contact: Contact? = Contact(
        name = name.value,
        phoneNo = number.value,
        email = email.value,
        dateOfEdit = Calendar.getInstance().timeInMillis,


        )
    LaunchedEffect(key1 = Unit) {
        if (contactId != null) {

            contact = (state.value as AppState.Data).data.find {
                it.id == contactId
            }
            Log.d("testting", contact.toString())
            name.value = contact?.name ?: ""
            number.value = contact?.phoneNo ?: ""
            email.value = contact?.email ?: ""



        }

    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF716B7E), // Blue background
                    titleContentColor = Color.Black      // Title text color
                ),


                title = {
                    Text(text = " Contact" )


                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                }

            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = Color(0xFF716B7E),
                modifier = Modifier
                    .height(65.dp)

            ){

            }
        }
    ) {
            innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("")
        }

    }


        Column(modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {

            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = modifier
                    .size(300.dp)


            )

            Spacer(modifier = modifier.height(16.dp))
            OutlinedTextField(value = name.value, onValueChange = { name.value = it },
                placeholder = {Text("name")},
                label = {Text("name")}
            )
            Spacer(modifier = modifier.height(16.dp))
            OutlinedTextField(value = number.value, onValueChange = { number.value = it },
                placeholder = {Text ("Number")},
                label = {Text("Number")})
            Spacer(modifier = modifier.height(16.dp))
            OutlinedTextField(value = email.value , onValueChange = { email.value = it},
                placeholder = {Text(text = "Email")},
                label = { Text(text = "Email") })
            Spacer(modifier = modifier.height(16.dp))
            Button(onClick = {
                Log.d("testting", contact.toString())
                var contact = Contact(
                    id = contactId ?: 0,
                    name = name.value,
                    phoneNo = number.value,
                    email = email.value,
                    dateOfEdit = Calendar.getInstance().timeInMillis,


                )
                contact.let { viewModel.upsertContact(it) }
                navController.navigateUp()


            }) {

                Text(text = "Save")
            }


        }
}



