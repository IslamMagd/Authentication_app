package com.example.auth.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.auth.R

val Inter = FontFamily(
    Font(R.font.inter_medium),
    Font(R.font.inter_regular)
)

val Roboto = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_medium),
    Font(R.font.roboto_semi_bold)
)

val HeadingLarge = TextStyle(
    fontFamily = Roboto,
    fontSize = 28.sp,
    fontWeight = FontWeight.W600,
    lineHeight = 36.sp
)

val ParagraphSmallRegular = TextStyle(
    fontFamily = Inter,
    fontSize = 14.sp,
    fontWeight = FontWeight.W400,
    lineHeight = 20.sp
)

val ParagraphSmallMedium = TextStyle(
    fontFamily = Inter,
    fontSize = 14.sp,
    fontWeight = FontWeight.W500,
    lineHeight = 20.sp
)

val LabelsLarge = TextStyle(
    fontFamily = Roboto,
    fontSize = 14.sp,
    fontWeight = FontWeight.W500,
    lineHeight = 20.sp
)

val HeadingMedium = TextStyle(
    fontFamily = Roboto,
    fontSize = 24.sp,
    fontWeight = FontWeight.W600,
    lineHeight = 32.sp
)

val HeadingTitle = TextStyle(
    fontFamily = Roboto,
    fontSize = 16.sp,
    fontWeight = FontWeight.W500,
    lineHeight = 24.sp
)

val BodyParagraph = TextStyle(
    fontFamily = Roboto,
    fontSize = 16.sp,
    fontWeight = FontWeight.W400,
    lineHeight = 24.sp
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)