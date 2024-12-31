package dev.balqadisha.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest reviewRequest) {
        if (reviewRequest.getReviewBody() == null || reviewRequest.getImdbId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Создание отзыва через сервис
        Review createdReview = reviewService.createReview(reviewRequest.getReviewBody(), reviewRequest.getImdbId());
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }
}
