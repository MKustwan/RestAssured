package pojos;

public class RequestToken {
    private String request_token;

    public RequestToken(String request_token) {
        this.request_token = request_token;
    }
    public RequestToken() {
    }
    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }
}
