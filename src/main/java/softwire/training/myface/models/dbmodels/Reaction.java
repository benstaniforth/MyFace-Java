package softwire.training.myface.models.dbmodels;

public class Reaction {
    private int id;
    private int postId;
    private String userName;
    private String type;

    public Reaction() {
    }

    public Reaction(int postId, String userName, String type) {
        this.postId = postId;
        this.userName = userName;
        this.type = type;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
