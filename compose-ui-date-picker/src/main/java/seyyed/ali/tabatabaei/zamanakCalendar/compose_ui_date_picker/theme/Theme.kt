package seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = lightColorScheme(
    primary = BluePrimary,
    secondary = BlueDark,
    onPrimary = White,
    background = White,
    onBackground = Black,
)

private val LightColorScheme = lightColorScheme(
    primary = BluePrimary,
    secondary = BlueLight,
    onPrimary = White,
    background = White,
    onBackground = Black,
)

@Composable
fun ZamanakCalendarDatePickerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content ,
    )
}