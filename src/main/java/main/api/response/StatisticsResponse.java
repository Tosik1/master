package main.api.response;

public class StatisticsResponse {

    private int postsCount;

    private int likesCount;

    private int dislikesCount;

    private int viewsCount;

    private long firstPublication;

    public StatisticsResponse(int postCount, int likeCount, int dislikeCount, int viewsCount, long firstPublication) {
        this.postsCount = postCount;
        this.likesCount = likeCount;
        this.dislikesCount = dislikeCount;
        this.viewsCount = viewsCount;
        this.firstPublication = firstPublication;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postCount) {
        this.postsCount = postCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likeCount) {
        this.likesCount = likeCount;
    }

    public int getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(int dislikeCount) {
        this.dislikesCount = dislikeCount;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public long getFirstPublication() {
        return firstPublication;
    }

    public void setFirstPublication(long firstPublication) {
        this.firstPublication = firstPublication;
    }
}
