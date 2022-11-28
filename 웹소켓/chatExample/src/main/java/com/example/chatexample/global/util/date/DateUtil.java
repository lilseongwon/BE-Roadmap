package com.example.chatexample.global.util.date;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DateUtil {

    public static String toTimeAgoFormat(LocalDateTime localDateTime) {

        LocalDateTime now = getZonedNow();

        if (localDateTime.getYear() != now.getYear()) {
            return yearDifference(now, localDateTime) + "년전";
        } else if (getIntMonth(localDateTime) != getIntMonth(now)) {
            return monthDifference(now, localDateTime) + "달전";
        } else if (localDateTime.getDayOfMonth() != now.getDayOfMonth()) {
            return dayDifference(now, localDateTime) + "일전";
        } else if (localDateTime.getHour() != now.getHour()) {
            return hourDifference(now, localDateTime) + "시간전";
        } else if (localDateTime.getMinute() != now.getMinute()) {
            return minuteDifference(now, localDateTime) + "분전";
        } else {
            return "방금전";
        }
    }

    private static int yearDifference(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return localDateTime1.minusYears(localDateTime2.getYear()).getYear();
    }

    private static int monthDifference(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return getIntMonth(localDateTime1.minusMonths(getIntMonth(localDateTime2)));
    }

    private static int getIntMonth(LocalDateTime localDateTime){
        return localDateTime.getMonth().getValue();
    }

    private static int dayDifference(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return localDateTime1.minusDays(localDateTime2.getDayOfMonth()).getDayOfMonth();
    }

    private static int hourDifference(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return localDateTime1.minusHours(localDateTime2.getHour()).getHour();
    }

    private static int minuteDifference(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return localDateTime1.minusMinutes(localDateTime2.getMinute()).getMinute();
    }

    public static LocalDateTime getZonedNow() {
        return LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public static String meridiemFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter
                                .ofPattern("a HH:mm")
                                .withLocale(Locale.forLanguageTag("ko")));
    }
}