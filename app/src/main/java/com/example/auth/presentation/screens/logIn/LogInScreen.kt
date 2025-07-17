package com.example.auth.presentation.screens.logIn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.auth.R
import com.example.auth.presentation.components.CustomButton
import com.example.auth.presentation.components.CustomTextField
import com.example.auth.presentation.components.PhoneTextField
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
        onNavigateToSelectCountry = onNavigateToSelectCountry
    )
}

@Composable
fun LoginContent(
    state: LoginUiState,
    executeIntent: (LogInIntent) -> Unit,
    onNavigateToSelectCountry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier

            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.5.dp, bottom = 51.dp, start = 16.dp, end = 16.dp)
    ) {
        LoginHeader()
        PhoneTextField(
            modifier = Modifier.padding(bottom = 32.dp, top = 81.dp),
            value = state.phone,
            onValueChange = { executeIntent(LogInIntent.EnterPhone(it, state.dialCode)) },
            dialCode = state.dialCode,
            flagResId = state.flagRes,
            onClickCountryPicker = onNavigateToSelectCountry,
            errorMessage = state.phoneError
        )
        PasswordField(
            password = state.password,
            onValueChange = { executeIntent(LogInIntent.EnterPassword(it)) }
        )
        Spacer(modifier = Modifier.weight(1f))
        LoginBottomActions(
            onSubmit = { executeIntent(LogInIntent.SubmitLogin) },
            isEnabled = state.isContinueButtonEnabled,
            onNavigateToSignUp = onNavigateToSelectCountry
        )
    }
}

@Composable
private fun LoginHeader() {
    Column(modifier = Modifier.padding(start = 7.dp, bottom = 10.5.dp)) {
        Text(
            text = stringResource(R.string.log_in),
            style = HeadingLarge,
            color = TextColorDarkDefault
        )
        Text(
            text = stringResource(R.string.please_fill_the_details_and_log_in),
            style = ParagraphSmallRegular,
            color = ColorsNeutral500
        )
    }
}

@Composable
private fun PasswordField(password: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.password),
            style = ParagraphSmallMedium,
            color = ColorsNeutral900,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = onValueChange,
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
    }
}

@Composable
private fun LoginBottomActions(
    onSubmit: () -> Unit,
    isEnabled: Boolean,
    onNavigateToSignUp: () -> Unit
) {
    Column {
        CustomButton(
            onClick = onSubmit,
            text = "Continue",
            enabled = isEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 13.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 31.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.you_don_t_have_an_account),
                style = ParagraphSmallRegular,
                color = ColorsNeutral900
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.create_account),
                style = ParagraphSmallMedium,
                color = TextUtilitiesTextOnOverlay,
                modifier = Modifier.clickable { onNavigateToSignUp() }
            )
        }
    }
}