package contest.collectingbox.global.common;

import static contest.collectingbox.global.exception.ErrorCode.NOT_FOUND_COLLECTING_BOX;

import contest.collectingbox.global.exception.CollectingBoxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtils {
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
