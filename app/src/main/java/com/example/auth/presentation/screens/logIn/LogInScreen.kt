package com.example.auth.presentation.screens.logIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.example.auth.R
import com.example.auth.presentation.components.CustomButton
import com.example.auth.presentation.components.CustomTextField
import com.example.auth.presentation.ui.theme.ColorsNeutral400
import com.example.auth.presentation.ui.theme.ColorsNeutral500
import com.example.auth.presentation.ui.theme.ColorsNeutral900
import com.example.auth.presentation.ui.theme.HeadingLarge
import com.example.auth.presentation.ui.theme.ParagraphSmallMedium
import com.example.auth.presentation.ui.theme.ParagraphSmallRegular
import com.example.auth.presentation.ui.theme.TextColorDarkDefault
import com.example.auth.presentation.ui.theme.TextUtilitiesTextOnOverlay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onNavigateToSignUp: () -> Unit,
    backStackEntry: NavBackStackEntry?,
    onNavigateToSelectCountry: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsState(initial = null)

    LaunchedEffect(effect.value) {
        when (effect.value) {
            is LogInEffect.NavigateToSignUp -> {
                onNavigateToSignUp()
            }
            is LogInEffect.NavigateToSelectCountry -> {
                onNavigateToSelectCountry()
            }
            else -> Unit
        }
    }

    LoginContent(
        state = state,
        executeIntent = viewModel::handleEvent,
        onNavigateToSelectCountry = onNavigateToSelectCountry,
        backStackEntry = backStackEntry
    )
}

@Composable
fun LoginContent(
    state: LoginUiState,
    executeIntent: (LogInIntent) -> Unit,
    onNavigateToSelectCountry: () -> Unit,
    backStackEntry: NavBackStackEntry?,
    modifier: Modifier = Modifier,
) {


    val isAllFieldsFilled = state.phone.isNotBlank() && state.password.isNotBlank()

    val dialCodeLive = backStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>("dialCode")
        ?.observeAsState()

    val flagResIdLive = backStackEntry
        ?.savedStateHandle
        ?.getLiveData<Int>("flagResId")
        ?.observeAsState()

    LaunchedEffect(dialCodeLive?.value) {
        dialCodeLive?.value?.let {
            executeIntent(LogInIntent.UpdateDialCode(it))
            backStackEntry.savedStateHandle.remove<String>("dialCode")
        }
    }

    LaunchedEffect(flagResIdLive?.value) {
        flagResIdLive?.value?.let {
            executeIntent(LogInIntent.UpdateFlagRes(it))
            backStackEntry.savedStateHandle.remove<Int>("flagResId")
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.5.dp, bottom = 51.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.log_in),
            style = HeadingLarge,
            color = TextColorDarkDefault,
            modifier = Modifier.padding(start = 7.dp, bottom = 10.5.dp)
        )
        Text(
            text = stringResource(R.string.please_fill_the_details_and_log_in),
            style = ParagraphSmallRegular,
            color = ColorsNeutral500
        )
        Spacer(modifier = Modifier.height(81.dp))
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.phone,
            onValueChange = { executeIntent(LogInIntent.EnterPhone(it, "+20")) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            isError = state.phoneError != null,
            supportingText = {
                state.phoneError?.let {
                    Text(text = it, color = Color.Red)
                }
            },
            leadingIcon = {
                Row(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .clickable { onNavigateToSelectCountry() }
                ) {
                    Image(
                        painter = painterResource(state.flagRes),
                        contentDescription = "flag image",
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = state.dialCode ?: "",
                        style = ParagraphSmallMedium,
                        color = ColorsNeutral900,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down),
                        contentDescription = "drop down icon",
                        tint = ColorsNeutral900
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.password),
            style = ParagraphSmallMedium,
            color = ColorsNeutral900,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password,
            onValueChange = { executeIntent(LogInIntent.EnterPassword(it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_password),
                    contentDescription = "password icon",
                    tint = ColorsNeutral900
                )
            },
            placeHolder = {
                Text(
                    text = stringResource(R.string.password),
                    style = ParagraphSmallRegular,
                    color = ColorsNeutral400
                )
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(
            onClick = { executeIntent(LogInIntent.SubmitLogin) },
            text = "Continue",
            enabled = isAllFieldsFilled,
            modifier = Modifier.padding(bottom = 13.dp)
        )
        Row(modifier = Modifier.padding(horizontal = 31.dp)) {
            Text(
                text = stringResource(R.string.you_don_t_have_an_account),
                style = ParagraphSmallRegular,
                color = ColorsNeutral900
            )
            Text(
                text = stringResource(R.string.create_account),
                style = ParagraphSmallMedium,
                color = TextUtilitiesTextOnOverlay
            )
        }
    }
}

//@Composable
//fun LoginScreenContent(modifier: Modifier = Modifier, executeIntent: (LogInIntent) -> Unit) {
//    executeIntent(LogInIntent.EnterPhone())
//}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginScreenPreview() {
//    LoginScreen()
}