package com.example.auth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.auth.presentation.screens.CountryPicker.CountryPickerScreen
import com.example.auth.presentation.screens.logIn.LogInIntent
import com.example.auth.presentation.screens.logIn.LoginScreen
import com.example.auth.presentation.screens.logIn.LoginViewModel
import com.example.auth.presentation.screens.signUp.SignUpContent
import com.example.auth.presentation.screens.signUp.SignUpScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AuthNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(route = Screen.LoginScreen.route) {
                backStackEntry ->
            val viewModel: LoginViewModel = koinViewModel()
            val dialCodeLive = backStackEntry
                .savedStateHandle
                .getLiveData<String>("dialCode")
                .observeAsState()

            val flagResIdLive = backStackEntry
                .savedStateHandle
                .getLiveData<Int>("flagResId")
                .observeAsState()

            LaunchedEffect(dialCodeLive.value) {
                dialCodeLive.value?.let {
                    viewModel.handleEvent(LogInIntent.UpdateDialCode(it))
                    backStackEntry.savedStateHandle.remove<String>("dialCode")
                }
            }

            LaunchedEffect(flagResIdLive.value) {
                flagResIdLive.value?.let {
                    viewModel.handleEvent(LogInIntent.UpdateFlagRes(it))
                    backStackEntry.savedStateHandle.remove<Int>("flagResId")
                }
            }

            LoginScreen(
                onNavigateToSignUp = { navController.navigate(Screen.SignUpScreen.route) },
                onNavigateToSelectCountry = { navController.navigate(Screen.SelectCountryScreen.route) },
                viewModel = viewModel
            )
        }

        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(
                onNavigateToSelectCountry = {
                    navController.navigate(Screen.SelectCountryScreen.route)
                }
            )
        }

        composable(route = Screen.SelectCountryScreen.route) { CountryPickerScreen(
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