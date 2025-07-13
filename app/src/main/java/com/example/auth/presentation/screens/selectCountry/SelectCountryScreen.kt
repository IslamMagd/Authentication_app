package com.example.auth.presentation.screens.selectCountry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.auth.R
import com.example.auth.presentation.components.CountryItem
import com.example.auth.presentation.components.HeaderRow

@Composable
fun SelectCountryScreen(modifier: Modifier = Modifier, onCountrySelected: (String, Int) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.5.dp, bottom = 51.dp, start = 16.dp)
    ) {
        HeaderRow(title = "Select Country")
        Spacer(modifier = Modifier.height(38.5.dp))
        CountryItem(
            flagResId = R.drawable.img_egypt_flag,
            phoneDial = "+20",
            country = "Egypt",
            onClick = {dialCode, flagResId -> onCountrySelected(dialCode, flagResId)}
        )
        CountryItem(
            flagResId = R.drawable.img_saudi_arabia_flag,
            phoneDial = "+966",
            country = "Saudi Arabia",
            onClick = {dialCode, flagResId -> onCountrySelected(dialCode, flagResId)}
        )
        CountryItem(
            flagResId = R.drawable.img_united_kingdom_flag,
            phoneDial = "+40",
            country = "United Kingdom",
            onClick = {dialCode, flagResId -> onCountrySelected(dialCode, flagResId)}
        )
        CountryItem(
            flagResId = R.drawable.img_canada_flag,
            phoneDial = "+1",
            country = "Canada",
            onClick = {dialCode, flagResId -> onCountrySelected(dialCode, flagResId)}
        )
        CountryItem(
            flagResId = R.drawable.img_france_flag,
            phoneDial = "+33",
            country = "France",
            onClick = {dialCode, flagResId -> onCountrySelected(dialCode, flagResId)}
        )
        CountryItem(
            flagResId = R.drawable.img_spain_flag,
            phoneDial = "+34",
            country = "Spain",
            onClick = {dialCode, flagResId -> onCountrySelected(dialCode, flagResId)}

        )
        CountryItem(
            flagResId = R.drawable.img_italy_flag,
            phoneDial = "+39",
            country = "Italy",
            onClick = {dialCode, flagResId -> onCountrySelected(dialCode, flagResId)}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectCountryScreenPreview() {
//    SelectCountryScreen()
}