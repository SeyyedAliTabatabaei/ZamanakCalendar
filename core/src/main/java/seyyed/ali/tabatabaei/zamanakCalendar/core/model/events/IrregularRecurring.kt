package seyyed.ali.tabatabaei.zamanakCalendar.core.model.events

internal data class IrregularRecurring(
    val calendar: String?,
    val day: Int?,
    val holiday: Boolean?,
    val month: Int?,
    val nth: Int?,
    val offset: Int?,
    val rule: String?,
    val title: String?,
    val type: String?,
    val weekday: Int?,
    val year: Int?
)