package seyyed.ali.tabatabaei.zamanakcalendar.model

/**
 * Data class representing a monthly event.
 *
 * This class encapsulates information about an event that occurs monthly,
 * including the day of the event, whether it is a holiday, the title of the event, and its source type.
 *
 * @property day The day of the month when the event occurs (default is 0).
 * @property isHoliday Indicates if the event is a holiday (default is false).
 * @property title The title of the event (default is an empty string).
 * @property type The source type of the event, represented by [EventSource] (default is EventSource.None).
 */
data class MonthlyEvent(
    /**
     * The day of the month when the event occurs (default is 0).
     * */
    val day: Int = 0,

    /**
     * Indicates if the event is a holiday (default is false).
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