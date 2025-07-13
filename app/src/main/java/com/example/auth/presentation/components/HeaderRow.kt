package com.example.auth.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.auth.R
import com.example.auth.presentation.ui.theme.HeadingLarge
import com.example.auth.presentation.ui.theme.TextColorDarkDefault

@Composable
fun HeaderRow(title: String, modifier: Modifier = Modifier) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
            contentDescription = "arrow left icon",
            tint = TextColorDarkDefault,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = title,
            style = HeadingLarge,
            color = TextColorDarkDefault
        )
    }
}