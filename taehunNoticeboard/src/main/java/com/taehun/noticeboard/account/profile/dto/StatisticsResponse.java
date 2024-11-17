package com.taehun.noticeboard.account.profile.dto;

public class StatisticsResponse {
    private long totalPost;
    private long totalComment;
    private long totalPostView;
    private String joinData;

    StatisticsResponse(final long totalPost, final long totalComment, final long totalPostView, final String joinData) {
        this.totalPost = totalPost;
        this.totalComment = totalComment;
        this.totalPostView = totalPostView;
        this.joinData = joinData;
    }

    public static StatisticsResponseBuilder builder() {
        return new StatisticsResponseBuilder();
    }

    public long getTotalPost() {
        return this.totalPost;
    }

    public long getTotalComment() {
        return this.totalComment;
    }

    public long getTotalPostView() {
        return this.totalPostView;
    }

    public String getJoinData() {
        return this.joinData;
    }

    public static class StatisticsResponseBuilder {
        private long totalPost;
        private long totalComment;
        private long totalPostView;
        private String joinData;

        StatisticsResponseBuilder() {
        }

        public StatisticsResponseBuilder totalPost(final long totalPost) {
            this.totalPost = totalPost;
            return this;
        }

        public StatisticsResponseBuilder totalComment(final long totalComment) {
            this.totalComment = totalComment;
            return this;
        }

        public StatisticsResponseBuilder totalPostView(final long totalPostView) {
            this.totalPostView = totalPostView;
            return this;
        }

        public StatisticsResponseBuilder joinData(final String joinData) {
            this.joinData = joinData;
            return this;
        }

        public StatisticsResponse build() {
            return new StatisticsResponse(this.totalPost, this.totalComment, this.totalPostView, this.joinData);
        }

        public String toString() {
            return "StatisticsResponse.StatisticsResponseBuilder(totalPost=" + this.totalPost + ", totalComment=" + this.totalComment + ", totalPostView=" + this.totalPostView + ", joinData=" + this.joinData + ")";
        }
    }
}
