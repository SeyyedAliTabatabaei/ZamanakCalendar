package seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.timePicker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.R
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.model.ZamanakTimePickerConfig
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.theme.ZamanakCalendarDatePickerTheme
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.utils.hideAndDismiss
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.Clock
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.ClockFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZamanakTimePickerBottomSheet(
    config: ZamanakTimePickerConfig = ZamanakTimePickerConfig(),
    bottomSheetState : SheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true ,
        confirmValueChange = { false }
    ),
    clockFormat: ClockFormat = ClockFormat.H24_HMS,
    selectedTimeTextStyle : TextStyle = TextStyle(
        fontFamily = config.fontFamily,
        fontSize = config.maxFontSize.sp,
    ),
    buttonColor: Color = Color.Blue ,
    backgroundColor : Color = MaterialTheme.colorScheme.background,
    onDismissBottomSheet : () -> Unit = {} ,
    onConfirm : (timeSelected : Clock) -> Unit
) {
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismissBottomSheet,
        sheetState = bottomSheetState ,
        containerColor = backgroundColor ,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp)
        ){
            ZamanakTimePickerBottomSheetContent(
                config = config ,
                clockFormat = clockFormat ,
                buttonColor = buttonColor,
                selectedTimeTextStyle = selectedTimeTextStyle,
                onCancel = {
                    scope.launch {
                        bottomSheetState.hideAndDismiss(onDismissBottomSheet)
                    }
                } ,
                onConfirm = { timeSelected ->
                    onConfirm(timeSelected)
                    scope.launch {
                        bottomSheetState.hideAndDismiss(onDismissBottomSheet)
                    }
                }
            )
        }
    }
}

@Composable
private fun ZamanakTimePickerBottomSheetContent(
    config : ZamanakTimePickerConfig,
    clockFormat: ClockFormat,
    buttonConfirmText : String = stringResource(R.string.confirm),
    buttonCancelText : String = stringResource(R.string.cancel),
    buttonColor : Color,
    selectedTimeTextStyle : TextStyle ,
    onCancel : () -> Unit = {},
    onConfirm : (timeSelected : Clock) -> Unit
) { 
    Column(
        modifier = Modifier.padding(vertical = 10.dp) ,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        var clockSelected by remember { mutableStateOf(config.defaultClock) }

        Text(
            text = clockSelected.format(clockFormat) ,
            style = selectedTimeTextStyle ,
            modifier = Modifier.padding(bottom = 15.dp)
        )

        ZamanakTimePicker(
            config = config ,
            timeSelected = {
                clockSelected = it
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(top = 20.dp) ,
            horizontalArrangement = Arrangement.Absolute.SpaceAround
        ) {
            Button(
                onClick = onCancel,
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = buttonColor
                ),
                border = BorderStroke(1.dp, buttonColor),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(buttonCancelText)
            }

            Button(
                onClick = { onConfirm(clockSelected) },
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor,
                    contentColor = Color.White ,
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(buttonConfirmText)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ZamanakTimePickerBottomSheetPreview() {
    ZamanakCalendarDatePickerTheme {
        ZamanakTimePickerBottomSheetContent(
            config = ZamanakTimePickerConfig() ,
            clockFormat = ClockFormat.H24_HMS ,
            buttonColor = Color.Blue ,
            selectedTimeTextStyle = TextStyle()

        ){

        }
    }
}