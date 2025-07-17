package com.example.auth.presentation.screens.CountryPicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.auth.presentation.ui.theme.ColorsGrey900
import com.example.auth.presentation.ui.theme.ColorsNeutral100
import com.example.auth.presentation.ui.theme.ColorsNeutral400
import com.example.auth.presentation.ui.theme.ParagraphSmallMedium
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CountryPickerScreen(
    viewModel: CountryPickerViewModel = koinViewModel(),
    onCountrySelected: (String, Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(CountryPickerIntent.LoadCountries)
    }

    SelectCountryContent(state = state, onCountrySelected = onCountrySelected)
}

@Composable
fun SelectCountryContent(
    state: CountryPickerUiState,
    onCountrySelected: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        state.errorMessage != null -> {
            Text(
                text = state.errorMessage,
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }

        else -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(top = 30.5.dp, bottom = 51.dp, start = 16.dp)
            ) {
                items(state.countries) { country ->
                    CountryItem(
                        countryUiState = country,
                        onClick = onCountrySelected
                    )
                }
            }
        }
    }
}

@Composable
fun CountryItem(
    countryUiState: CountryUiState,
    modifier: Modifier = Modifier,
    onClick: (String, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(countryUiState.dialCode, countryUiState.flag) }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 15.5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = countryUiState.flag),
                contentDescription = null,
                modifier = Modifier
                    .height(17.14.dp)
                    .width(24.dp)
                    .padding(end = 6.dp)
            )
            Text(
                text = countryUiState.dialCode,
                style = ParagraphSmallMedium,
                color = ColorsNeutral400,
                modifier = Modifier.padding(end = 14.dp)
            )
            Text(text = countryUiState.name, style = ParagraphSmallMedium, color = ColorsGrey900)
        }
        Spacer(modifier = Modifier.height(15.dp))
        HorizontalDivider(thickness = 1.dp, color = ColorsNeutral100)
    }
}
