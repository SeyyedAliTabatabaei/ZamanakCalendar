package seyyed.ali.tabatabaei.zamanakCore.model.events

internal data class EventCalendar(
    val day: Int?,
    val holiday: Boolean?,
    val month: Int?,
    val title: String?,
    val type: String?
)