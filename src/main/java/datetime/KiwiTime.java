package datetime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Custom time class that wraps a java.time.LocalTime object. Should only be called by KiwiDateTime class.
 */
public class KiwiTime {
    private LocalTime time;
    private final boolean isEmpty;
    private boolean isHourOnly;

    private KiwiTime(int hour, int minute) {
        this.time = LocalTime.of(hour, minute);
        this.isEmpty = false;
        checkHourOnly();
    }

    public static KiwiTime of(int hour, int minute) {
        return new KiwiTime(hour, minute);
    }

    public static KiwiTime of(int hour) {
        return new KiwiTime(hour, 0);
    }

    private void checkHourOnly() {
        this.isHourOnly = (this.time.getMinute() == 0);
    }


    @Override
    public String toString() {

        DateTimeFormatter f;

        if (isHourOnly) {
            f = DateTimeFormatter.ofPattern("ha");
        } else {
            f = DateTimeFormatter.ofPattern("h:ma");
        }

        return this.time.format(f).toLowerCase();
    }


    String unparse(String delimiter) {
        return String.format("%d%s%d", this.time.getHour(), delimiter, this.time.getMinute());
    }

}
