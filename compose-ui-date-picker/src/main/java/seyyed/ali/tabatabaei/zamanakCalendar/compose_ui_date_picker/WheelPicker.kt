@file:OptIn(ExperimentalFoundationApi::class)

package seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker


import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.theme.ZamanakCalendarDatePickerTheme
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.math.sign

@Composable
internal fun <T>WheelPicker(
    items: List<T>,
    initialSelectedIndex: Int = 0,
    modifier: Modifier = Modifier,
    itemHeight: Dp = 30.dp,
    visibleItemsCount: Int = 5,
    maxRotation: Float = 70f,
    maxFontSize: Float = 22f,
    minFontSize: Float = 14f,
    fontFamily: FontFamily ?= null,
    textColor: Color = Color.Gray ,
    textColorSelected: Color = MaterialTheme.colorScheme.primary ,
    focusBackground : @Composable (() -> Unit?)?= {},
    onItemSelected : (item : T?, positionItemSelected : Int?) -> Unit ,
) {
    val listState = rememberLazyListState()
    val centerIndex = visibleItemsCount / 2
    val itemHeightPx = with(LocalDensity.current) { itemHeight.toPx() }
    val itemSelectedPos = remember { mutableIntStateOf(initialSelectedIndex) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(!listState.isScrollInProgress) {
        listState.animateScrollToItem(itemSelectedPos.intValue)
        onItemSelected(items.getOrNull(itemSelectedPos.intValue) , itemSelectedPos.intValue)
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.isNotEmpty() }
            .filter { it }
            .first()
        listState.scrollToItem(initialSelectedIndex)
        onItemSelected(items.getOrNull(itemSelectedPos.intValue) , itemSelectedPos.intValue)
    }

    Box(
        modifier = modifier
            .height(itemHeight * visibleItemsCount)
    ) {
        Box(contentAlignment = Alignment.Center , modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight)
            .align(Alignment.Center)
        ) {
            focusBackground?.let { it() }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = itemHeight * centerIndex),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            itemsIndexed(items) { index, item ->
                val relativeIndex = index - listState.firstVisibleItemIndex -
                        (listState.firstVisibleItemScrollOffset / itemHeightPx)

                val maxDistance = (visibleItemsCount / 2).toFloat()
                val distanceFromCenter = relativeIndex.absoluteValue.coerceAtMost(maxDistance)
                val rotationFactor = distanceFromCenter / maxDistance
                val clampedRotation = (rotationFactor * maxRotation) * relativeIndex.sign

                val fontSize = lerp(maxFontSize, minFontSize, rotationFactor)
                val selected = distanceFromCenter < 0.5f
                if (selected) itemSelectedPos.intValue = index

                Box(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        coroutineScope.launch {
                            listState.animateScrollToItem(index)
                        }
                    },
                ) {
                    Item(
                        text = item.toString(),
                        rotation = clampedRotation,
                        fontSize = fontSize,
                        selected = selected,
                        height = itemHeight,
                        fontFamily = fontFamily ,
                        textColor = textColor ,
                        textColorSelected = textColorSelected
                    )
                }
            }
        }
    }
}

fun lerp(start: Float, end: Float, fraction: Float): Float {
    return start + (end - start) * fraction
}

@Composable
fun Item(
    text: String,
    rotation: Float,
    fontSize: Float,
    selected: Boolean,
    height: Dp ,
    fontFamily: FontFamily?,
    textColor: Color,
    textColorSelected: Color,
) {
    val density = LocalDensity.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .graphicsLayer {
                rotationX = rotation
                transformOrigin = TransformOrigin(0.5f, 0.5f)
                cameraDistance = 12 * density.density
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = fontFamily,
                fontSize = fontSize.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                color = if (selected) textColorSelected else textColor
            )
        )
    }
}

@Preview(showBackground = true , showSystemUi = true)
@Composable
private fun ZamanakDatePickerPreview() {
    ZamanakCalendarDatePickerTheme {
        val numbers = (0..30).map { it.toString() }
        WheelPicker(items = numbers){_,_ ->

        }
    }
}