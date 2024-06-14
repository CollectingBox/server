package contest.collectingbox.module.review.application;

import static contest.collectingbox.global.exception.ErrorCode.NOT_FOUND_COLLECTING_BOX;

import contest.collectingbox.global.exception.CollectingBoxException;
import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import contest.collectingbox.module.collectingbox.domain.repository.CollectingBoxRepository;
import contest.collectingbox.module.review.domain.ReviewRepository;
import contest.collectingbox.module.review.dto.ReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final CollectingBoxRepository collectingBoxRepository;

    public Long postReview(ReviewRequest dto, Long collectionId) {
        CollectingBox box = collectingBoxRepository.findById(collectionId)
                .orElseThrow(() -> new CollectingBoxException(NOT_FOUND_COLLECTING_BOX));
        return reviewRepository.save(dto.toEntity(box)).getId();
    }
}
