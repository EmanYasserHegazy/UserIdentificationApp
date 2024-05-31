package com.user.identificationapp.ui.navigation

sealed class NavigationItem(val route: String) {
    object Splash : NavigationItem(Screen.SPLASH.name)
    object UserScreen : NavigationItem(Screen.USER_SCREEN.name)
    object UserList : NavigationItem(Screen.USER_LIST.name)
    object UserDetails : NavigationItem(Screen.USER_DETAILS.name)
}

enum class Screen {
    SPLASH,
    USER_SCREEN,
    USER_LIST,
    USER_DETAILS,
}
