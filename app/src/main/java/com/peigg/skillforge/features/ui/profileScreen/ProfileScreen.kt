package com.peigg.skillforge.features.ui.profileScreen

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.peigg.skillforge.R
import com.peigg.skillforge.features.ui.skillForgeScreen.AppTopBar


@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val state by  viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            AppTopBar(snackbarHostState)
        }
    ) { innerPadding ->

        Box (modifier = Modifier.padding(innerPadding)){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))


                when (state){
                    is ProfileState.Success ->  ProfileContent(state as ProfileState.Success) { event, isChecked ->
                        viewModel.onCheckedChange(event, isChecked)
                    }
                    ProfileState.Loading -> {
                        ProfileLoading()
                    }

                }
            }
        }
    }
}
@Composable
fun ProfileContent(state: ProfileState.Success, onCheckedChange: (ProfileEvent, Boolean) -> Unit) {
    Column(Modifier.padding(90.dp)) {
        CheckingData(state.profile, R.string.profile) { isChecked -> onCheckedChange(ProfileEvent.Profile, isChecked) }
        CheckingData(state.notifications, R.string.notifications) { isChecked -> onCheckedChange(ProfileEvent.Notifications, isChecked) }
        CheckingData(state.name, R.string.name) { isChecked -> onCheckedChange(ProfileEvent.Name, isChecked) }
        CheckingData(state.email, R.string.email) { isChecked -> onCheckedChange(ProfileEvent.Email, isChecked) }
        CheckingData(state.phone, R.string.phone) { isChecked -> onCheckedChange(ProfileEvent.Phone, isChecked) }
    }
}

@Composable
fun CheckingData(state: Boolean, @StringRes text: Int, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(stringResource(id = text), style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(checked = state, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun ProfileLoading(){
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Loading...")
            Spacer(modifier = Modifier.padding(16.dp))
            CircularProgressIndicator()
        }
    }
}