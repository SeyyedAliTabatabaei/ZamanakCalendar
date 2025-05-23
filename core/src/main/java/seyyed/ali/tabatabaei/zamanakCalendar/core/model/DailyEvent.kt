package seyyed.ali.tabatabaei.zamanakCalendar.core.model


/**
 * Data class representing a daily event.
 *
 * This class encapsulates information about an event that occurs daily,
 * including whether it is a holiday, the title of the event, and its source type.
 *
 * @property isHoliday Indicates if the event is a holiday (default is false).
 * @property title The title of the event (default is an empty string).
 * @property type The source type of the event, represented by [EventSource] (default is EventSource.None).
 */
data class DailyEvent(
    /**
     * Indicates if the event is a holiday (default is false)
     * */
    val isHoliday: Boolean = false,

    /**
     * The title of the event (default is an empty string).
     * */
    val title: String = "",

    /**
     * The source type of the event (default is EventSource.None).
     * */
    val type: EventSource = EventSource.None
)