package seyyed.ali.tabatabaei.zamanakcalendar.model.events

import com.google.gson.annotations.SerializedName

internal data class Events(
    @SerializedName("Gregorian Calendar") val gregorianCalendar: List<EventCalendar>?,
    @SerializedName("Hijri Calendar") val hijriCalendar: List<EventCalendar>?,
    @SerializedName("Irregular Recurring") val irregularRecurring: List<IrregularRecurring>?,
    @SerializedName("Nepali Calendar") val nepaliCalendar: List<EventCalendar>?,
    @SerializedName("Persian Calendar") val persianCalendar: List<EventCalendar>?,
)