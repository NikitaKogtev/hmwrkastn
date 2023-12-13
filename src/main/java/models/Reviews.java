package models;

public class Reviews {
    private String review;
    private int score;

    public Reviews(String review, int score) {
        this.review = review;
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
