package com.example.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.auth.R
import com.example.auth.presentation.ui.theme.ColorsNeutral900
import com.example.auth.presentation.ui.theme.ParagraphSmallMedium

@Composable
fun PhoneTextField(
    value: String,
    onValueChange: (String) -> Unit,
    dialCode: String,
    flagResId: Int,
    onClickCountryPicker: () -> Unit,
    errorMessage: String,
    modifier: Modifier = Modifier
) {
    CustomTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        isError = errorMessage.isNotEmpty(),
        supportingText = { Text(text = errorMessage, color = Color.Red) },
        leadingIcon = {
            Row(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .clickable { onClickCountryPicker() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = flagResId),
                    contentDescription = "flag image",
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = dialCode,
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
}
