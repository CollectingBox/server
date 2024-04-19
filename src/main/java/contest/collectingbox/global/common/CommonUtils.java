package contest.collectingbox.global.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {
    public static String formatDate(String inputDate) {
        LocalDateTime dateTime = LocalDateTime.parse(inputDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSS"));
        return dateTime.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
    }
}
