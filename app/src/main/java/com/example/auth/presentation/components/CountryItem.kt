package com.example.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.auth.presentation.ui.theme.ColorsGrey900
import com.example.auth.presentation.ui.theme.ColorsNeutral100
import com.example.auth.presentation.ui.theme.ColorsNeutral400
import com.example.auth.presentation.ui.theme.ParagraphSmallMedium

@Composable
fun CountryItem(
    flagResId: Int,
    phoneDial: String,
    country: String,
    modifier: Modifier = Modifier,
    onClick: (String, Int) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable{ onClick(phoneDial, flagResId) }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 15.5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = flagResId),
                contentDescription = null,
                modifier = Modifier
                    .height(17.14.dp)
                    .width(24.dp)
                    .padding(end = 6.dp)
            )
            Text(
                text = phoneDial,
                style = ParagraphSmallMedium,
                color = ColorsNeutral400,
                modifier = Modifier.padding(end = 14.dp)
            )
            Text(text = country, style = ParagraphSmallMedium, color = ColorsGrey900)
        }
        Spacer(modifier = Modifier.height(15.dp))
        HorizontalDivider(thickness = 1.dp, color = ColorsNeutral100)
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryItemPreview() {
//    CountryItem(
//        flag = painterResource(R.drawable.img_egypt_flag),
//        phoneDial = "+20",
//        country = "Egypt"
//    )
}