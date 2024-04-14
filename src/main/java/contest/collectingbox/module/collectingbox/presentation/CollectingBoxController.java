package contest.collectingbox.module.collectingbox.presentation;

import contest.collectingbox.module.collectingbox.application.CollectingBoxService;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collections")
public class CollectingBoxController {

    private final CollectingBoxService collectingBoxService;

    @GetMapping
    public List<CollectingBoxResponse> findCollectingBoxesWithinArea(@RequestParam Double latitude,
                                                                     @RequestParam Double longitude,
                                                                     @RequestParam List<Tag> tags) {
        return collectingBoxService.findCollectingBoxesWithinArea(latitude, longitude, tags);
    }
}
