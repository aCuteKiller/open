package bai.demo.searchThink.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-18 12:40
 * @description 字符串工具
 */
public class StringUtil {
    public static class CommonFormatter {
        public static final DateTimeFormatter DateAndTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        public static final DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        public static final DateTimeFormatter TimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        public static final DateTimeFormatter UUIDPrefixFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-");
    }
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(CommonFormatter.DateAndTimeFormatter);
    }

    public static String formatDateTime(LocalDateTime dateTime, DateTimeFormatter formatter) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(formatter);
    }
}
