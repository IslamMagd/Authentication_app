package com.example.auth.presentation.screens.signUp

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
import com.example.auth.R
import com.example.auth.presentation.components.CustomButton
import com.example.auth.presentation.components.CustomTextField
import com.example.auth.presentation.components.HeaderRow
import com.example.auth.presentation.components.PhoneTextField
import com.example.auth.presentation.screens.logIn.LogInIntent
import com.example.auth.presentation.ui.theme.BodyParagraph
import com.example.auth.presentation.ui.theme.ColorsNeutral900
import com.example.auth.presentation.ui.theme.HeadingMedium
import com.example.auth.presentation.ui.theme.HeadingTitle
import com.example.auth.presentation.ui.theme.ParagraphSmallMedium
import com.example.auth.presentation.ui.theme.TextColorDarkDefault
import com.example.auth.presentation.ui.theme.TextColourDarkHint
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpScreen(
    onNavigateToSelectCountry: () -> Unit,
    viewModel: SignUpViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsState(initial = null)

    LaunchedEffect(effect.value) {
        when (effect.value) {
            is SignUpEffect.NavigateToSelectCountry -> onNavigateToSelectCountry()
            else -> Unit
        }
    }

    SignUpContent(
        state = state,
        executeIntent = viewModel::handleEvent,
        onNavigateToSelectCountry = onNavigateToSelectCountry
    )
}
@Composable
fun SignUpContent(
    state: SignUpUiState,
    executeIntent: (SignUpIntent) -> Unit,
    onNavigateToSelectCountry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.5.dp, bottom = 51.dp, start = 16.dp, end = 16.dp)
    ) {
        HeaderRow(title = "Sign up")
        Spacer(modifier = Modifier.height(38.5.dp))
        Text(
            text = stringResource(R.string.personal_details),
            style = HeadingMedium,
            color = TextColorDarkDefault
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.name),
            style = HeadingTitle,
            color = TextColorDarkDefault,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.name,
            onValueChange = { executeIntent(SignUpIntent.EnterName(it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            isError = state.nameError.isNotEmpty(),
            supportingText = {
                if (state.nameError.isNotEmpty())
                {
                    Text(text = state.nameError, color = Color.Red)
                }
            },
            placeHolder = {
                Text(
                    text = stringResource(R.string.input_text_here),
                    style = BodyParagraph,
                    color = TextColourDarkHint
                )
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.surname),
            style = HeadingTitle,
            color = TextColorDarkDefault,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.surName,
            onValueChange = { executeIntent(SignUpIntent.EnterSurName(it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            isError = state.surNameError.isNotEmpty(),
            supportingText = {
                if (state.surName.isNotEmpty())
                {
                    Text(text = state.surNameError, color = Color.Red)
                }
            },
            placeHolder = {
                Text(
                    text = stringResource(R.string.input_text_here),
                    style = BodyParagraph,
                    color = TextColourDarkHint
                )
            }
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Email*",
            style = HeadingTitle,
            color = TextColorDarkDefault,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email,
            onValueChange = { executeIntent(SignUpIntent.EnterEmail(it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = state.emailError.isNotEmpty(),
            supportingText = {
                if (state.emailError.isNotEmpty()) {
                    Text(text = state.emailError, color = Color.Red)
                }
            },
            placeHolder = {
                Text(
                    text = stringResource(R.string.input_text_here),
                    style = BodyParagraph,
                    color = TextColourDarkHint
                )
            }
        )
        Spacer(modifier = Modifier.height(32.dp))

        PhoneTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            value = state.phone,
            onValueChange = { executeIntent(SignUpIntent.EnterPhone(it, state.dialCode)) },
            dialCode = state.dialCode,
            flagResId = state.flagRes,
            onClickCountryPicker = onNavigateToSelectCountry,
            errorMessage = state.phoneError
        )
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(
            onClick = {
                executeIntent(SignUpIntent.SubmitSignUp)
            },
            text = stringResource(R.string.confirm),
            modifier = Modifier.padding(bottom = 13.dp),
            enabled = state.isAllFieldsFilled
        )

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(onNavigateToSelectCountry = {})
}