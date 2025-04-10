package seyyed.ali.tabatabaei.zamanak.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import seyyed.ali.tabatabaei.zamanak.R

val CustomFontFamily = FontFamily(
    Font(R.font.regilar)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = CustomFontFamily ,
        fontSize = 16.sp
    ) ,
    bodySmall = TextStyle(
        fontFamily = CustomFontFamily ,
        fontSize = 12.sp
    ) ,
    bodyMedium = TextStyle(
        fontFamily = CustomFontFamily ,
        fontSize = 14.sp
    )
)