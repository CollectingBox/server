package contest.collectingbox.module.publicdata;

import contest.collectingbox.module.collectingbox.domain.Tag;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoadCsvPublicDataRequest {

    private String sigungu;
    private String fileName;
    private Tag tag;
}
