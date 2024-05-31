package com.user.identificationapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.user.identificationapp.R
import com.user.identificationapp.domain.model.User

@Composable
fun UserDetailsScreen(navHostController: NavHostController) {
    val userModel = navHostController.currentBackStackEntry?.arguments?.getString("userModel")
    val user = Gson().fromJson(userModel, User::class.java)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {

        Text(
            text = stringResource(id = R.string.user_details_title),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
            color = Color.White,
            maxLines = 1
        )

        Icon(
            Icons.Rounded.AccountBox,
            contentDescription = "Localized description",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp)
                .size(255.dp),
            tint = Color.White
        )

        Column(
            modifier = Modifier.padding(start = 32.dp, top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Name: ${user?.name}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Age: ${user?.age}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Gender: ${user?.gender}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Job Title: ${user?.userPosition}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
