package com.user.identificationapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.user.identificationapp.domain.model.User
import com.user.identificationapp.features.get_user_feature.vm.UserListViewModel
import com.user.identificationapp.ui.navigation.Screen

@Composable

fun UserListScreen(
    navHostController: NavHostController, userListViewModel: UserListViewModel = hiltViewModel()
) {

    userListViewModel.users.value?.let { UserList(users = it, navHostController) }
}

@Composable
fun UserItem(user: User) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 4.dp,

        ) {
        Column(
            modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Name: ${user.name}",
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
            Text(
                text = "Age: ${user.age}",
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
            Text(
                text = "Gender: ${user.gender}",
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
            Text(
                text = "Job Title: ${user.userPosition}",
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
            Icon(
                Icons.Rounded.KeyboardArrowRight,
                contentDescription = "Localized description",
                modifier = Modifier
                    .align(Alignment.End)
                    .size(35.dp)
            )

        }
    }
}

@Composable
fun UserList(users: List<User>, navHostController: NavHostController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(users) { user ->
            Box(Modifier.clickable {
                val userModel = Gson().toJson(user)
                navHostController.navigate("${Screen.USER_DETAILS.name}/${userModel}")

            }) {
                UserItem(user = user)
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
    }
}

