package com.sravan.covidapplication.Activities.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sravan.covidapplication.R


val gillSans = FontFamily(
    Font(R.font.gs_bold, FontWeight.Bold),
    Font(R.font.gs_light, FontWeight.Normal),
    Font(R.font.gs_medium, FontWeight.Medium)

)
// Set of Material typography styles to start with
val myCustomTypography = Typography(
    body1 = TextStyle(
        fontFamily = gillSans,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp),
    body2 = TextStyle(
        fontFamily = gillSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp),
    h1 = TextStyle(
        fontFamily = gillSans,
        fontWeight = FontWeight.Light,
        fontSize = 100.sp),
    subtitle1 = TextStyle(
        fontFamily = gillSans,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)