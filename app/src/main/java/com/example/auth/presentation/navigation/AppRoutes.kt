package com.example.auth.presentation.navigation

sealed class Screen(val route: String) {
    object LoginScreen: Screen(route = "log_in")
    object SignUpScreen: Screen(route = "sign_up")
    object SelectCountryScreen: Screen(route = "select_country")
}




