package com.example.auth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.auth.presentation.screens.logIn.LoginScreen
import com.example.auth.presentation.screens.selectCountry.SelectCountryScreen
import com.example.auth.presentation.screens.signUp.SignUpScreen

@Composable
fun AuthNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(route = Screen.LoginScreen.route) {
            val backStackEntry = remember(it) {
                navController.currentBackStackEntry
            }
            LoginScreen(
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUpScreen.route)
                },
                onNavigateToSelectCountry = {
                    navController.navigate(Screen.SelectCountryScreen.route)
                },
                backStackEntry = backStackEntry
            )
        }

        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(
                onNavigateToSelectCountry = {
                    navController.navigate(Screen.SelectCountryScreen.route)
                }
            )
        }

        composable(route = Screen.SelectCountryScreen.route) { SelectCountryScreen(
            onCountrySelected = { dialCode, flagResId ->
                navController.previousBackStackEntry
                    ?.savedStateHandle?.set("dialCode", dialCode)
                navController.previousBackStackEntry
                    ?.savedStateHandle?.set("flagResId", flagResId)
                navController.popBackStack()
            }
        ) }
    }
}