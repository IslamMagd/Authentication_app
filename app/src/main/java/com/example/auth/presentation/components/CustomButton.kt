package com.example.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.auth.presentation.ui.theme.BackgroundUtilitiesOverlay
import com.example.auth.presentation.ui.theme.LabelsLarge
import com.example.auth.presentation.ui.theme.TextColourLightDefault

@Composable
fun CustomButton(onClick: () -> Unit,enabled: Boolean, modifier: Modifier = Modifier, text: String) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(BackgroundUtilitiesOverlay)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = text,
            style = LabelsLarge,
            color = TextColourLightDefault,
        )
    }
}