package dev.balqadisha.movies;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {
        if (reviewBody == null || imdbId == null || reviewBody.isEmpty() || imdbId.isEmpty()) {
            throw new IllegalArgumentException("Review body and imdbId must not be null or empty");
        }

        Review review = reviewRepository.save(new Review(reviewBody));

        UpdateResult updateResult = mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review.getId()))
                .first();

        if (updateResult.getModifiedCount() == 0) {
            throw new RuntimeException("Failed to associate review with movie");
        }

        return review;
    }
}
