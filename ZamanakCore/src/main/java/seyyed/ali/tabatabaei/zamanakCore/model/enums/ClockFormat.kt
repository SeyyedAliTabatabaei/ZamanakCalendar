package seyyed.ali.tabatabaei.zamanakCore.model.enums

enum class ClockFormat {
    /**
     * 24-hour format without seconds: 13:45
     */
    H24_HM,

    /**
     * 24-hour format with seconds: 13:45:30
     */
    H24_HMS,

    /**
     * 12-hour format without seconds and AM/PM: 01:45
     */
    H12_HM,

    /**
     * 12-hour format without seconds and with AM/PM: 01:45 PM
     */
    H12_HM_AMPM,

    /**
     * 12-hour format with seconds and AM/PM: 01:45:30
     */
    H12_HMS,

    /**
     * 12-hour format with seconds and with AM/PM: 01:45:30 PM
     */
    H12_HMS_AMPM,

    /**
     * Only hour and AM/PM: 01 PM
     */
    H12_H_AMPM,

    /**
     * Compact 24-hour format with no separator: 134530
     */
    H24_COMPACT
}