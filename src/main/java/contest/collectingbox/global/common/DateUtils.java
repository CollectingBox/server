package contest.collectingbox.global.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class DateUtils {
    public static String formatDate(String inputDate) {
        try{
            LocalDateTime dateTime = LocalDateTime.parse(inputDate,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
            return dateTime.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
        }catch(DateTimeParseException e){
            log.error("exception message = {}", e.getMessage());
            return null;
        }
    }
}
