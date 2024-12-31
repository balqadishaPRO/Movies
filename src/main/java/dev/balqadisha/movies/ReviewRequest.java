package dev.balqadisha.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private String reviewBody;
    private String imdbId;

    public String getReviewBody() {
        return reviewBody;
    }

    public String getImdbId() {
        return imdbId;
    }
}
