package contest.collectingbox.module.collectingbox.dto;

import com.querydsl.core.annotations.QueryProjection;
import contest.collectingbox.module.collectingbox.domain.Tag;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class CollectingBoxDetailResponse {
    private String location;
    private String roadName;
    private String streetNumber;
    private String modifiedDate;
    private String tag;

    @QueryProjection
    public CollectingBoxDetailResponse(String location, String roadName, String streetNumber, String modifiedDate,
                                       String tag) {
        this.location = location;
        this.roadName = roadName;
        this.streetNumber = streetNumber;
        this.modifiedDate = formatDate(modifiedDate);
        this.tag = Tag.of(tag);
    }

    private String formatDate(String inputDate) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        SimpleDateFormat output = new SimpleDateFormat("yyyy.MM.dd");
        try {
            Date date = input.parse(inputDate);
            return output.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
