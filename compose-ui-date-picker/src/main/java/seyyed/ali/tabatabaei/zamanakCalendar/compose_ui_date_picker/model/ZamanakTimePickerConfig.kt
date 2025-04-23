package seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.Clock


data class ZamanakTimePickerConfig(
    val unfocusedCount: Int = 2,
    val maxRotation: Float = 70f,
    val maxFontSize: Float = 20f,
    val minFontSize: Float = 12f,
    val fontFamily: FontFamily? = null,
    val textColor: Color = Color.Gray,
    val textColorSelected: Color = Color.Blue,
    val itemHeight: Dp = 30.dp,
    val showHourPicker: Boolean = true,
    val showMinutePicker: Boolean = true,
    val showSecondPicker: Boolean = true,
    val is24HourFormat: Boolean = true,
    val amText: String = "AM",
    val pmText: String = "PM",
    val defaultClock: Clock = Clock(),
    val focusBackground: @Composable (() -> Unit?)? = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(alpha = 0.3f))
        )
    }
)
