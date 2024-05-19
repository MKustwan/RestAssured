package endpoints;

public enum EndPoint {
    ADDFAVOURITEMOVIE("/3/account/{account_id}/favorite"),
    GETMOVIELIST("/3/genre/movie/list"),
    GETACCOUNTDETAILS("/3/account/{account_id}"),
    GETFAVORITEMOVIE("/3/account/{account_id}/favorite/movies"),
    ADDFAVORITEMOVIE("3/account/{account_id}/favorite"),
    GETLISTS("/3/movie/{movie_id}/lists"),
    CREATESESSION("/3/authentication/session/convert/4"),
    CREATELIST("/3/list"),
    CREATEREQUESTTOKEN("/4/auth/request_token"),
    AUTHENTICATION("/authenticate/"),
    ACCESSTOKEN("/4/auth/access_token"),
    ADDMOVIE("/3/list/{list_id}/add_item"),
    GETLISTDETAIL("/3/list/{list_id}"),
    DELETELIST("/3/list/{list_id}"),
    CHECKITEMSTATUS("/3/list/{list_id}/item_status");
    final String endpoint;

    EndPoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
