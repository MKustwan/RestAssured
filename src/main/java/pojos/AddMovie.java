package pojos;

public class AddMovie {
    private String media_id;

    public AddMovie(String media_id) {
        this.media_id = media_id;
    }
    public AddMovie() {
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
}
