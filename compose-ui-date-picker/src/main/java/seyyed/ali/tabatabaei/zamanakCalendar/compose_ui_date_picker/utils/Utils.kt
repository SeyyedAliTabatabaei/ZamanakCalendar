package seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.utils

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState

@OptIn(ExperimentalMaterial3Api::class)
internal suspend fun SheetState.hideAndDismiss(onDismiss: () -> Unit) {
    this.hide()
    onDismiss()
}