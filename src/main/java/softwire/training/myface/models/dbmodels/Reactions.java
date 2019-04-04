package softwire.training.myface.models.dbmodels;

public class Reactions {
    private int id;
    private int postId;
    private String userName;
    private Boolean wave;
    private Boolean likes;
    private Boolean frown;
    private Boolean angry;
    private Boolean laughing;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getWave() {
        return wave;
    }

    public void setWave(Boolean wave) {
        this.wave = wave;
    }

    public Boolean getLikes() {
        return likes;
    }

    public void setLikes(Boolean likes) {
        this.likes = likes;
    }

    public Boolean getFrown() {
        return frown;
    }

    public void setFrown(Boolean frown) {
        this.frown = frown;
    }

    public Boolean getAngry() {
        return angry;
    }

    public void setAngry(Boolean angry) {
        this.angry = angry;
    }

    public Boolean getLaughing() {
        return laughing;
    }

    public void setLaughing(Boolean laughing) {
        this.laughing = laughing;
    }
}
