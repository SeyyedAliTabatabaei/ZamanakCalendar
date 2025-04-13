package seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums

/**
 * Enum class representing the type of hour.
 *
 * This enum is used to distinguish between the two halves of the 24-hour day:
 * AM (Ante Meridiem) and PM (Post Meridiem).
 *
 * @property AM Represents the time from midnight to noon (12:00 AM to 11:59 AM).
 * @property PM Represents the time from noon to midnight (12:00 PM to 11:59 PM).
 */
enum class HourType {
    /**
     * Represents the time from midnight to noon (12:00 AM to 11:59 AM)
     * */
    AM,


    /**
     * Represents the time from noon to midnight (12:00 PM to 11:59 PM)
     * */
    PM,
}