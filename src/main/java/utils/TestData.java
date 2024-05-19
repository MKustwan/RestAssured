package utils;

import pojos.*;

public class TestData {
    public static FavouriteMovie addFavouriteMovie(String media_type, String media_id, Boolean favorite) {
        FavouriteMovie favouriteMovie = new FavouriteMovie();
        favouriteMovie.setMedia_type(media_type);
        favouriteMovie.setMedia_id(media_id);
        favouriteMovie.setFavorite(favorite);
        return favouriteMovie;
    }

    public static RedirectTo addRedirectTo(String redirectTo) {
        RedirectTo redirectTo1 = new RedirectTo();
        redirectTo1.setRedirect_to(redirectTo);
        return redirectTo1;
    }

    public static RequestToken addRequestToken(String request_token) {
        RequestToken requestToken = new RequestToken();
        requestToken.setRequest_token(request_token);
        return requestToken;
    }

    public static AccessToken addAccessToken(String access_token) {
        AccessToken accessToken = new AccessToken();
        accessToken.setAccess_token(access_token);
        return accessToken;
    }

    public static CreateList createList(String name, String description, String language) {
        CreateList createList = new CreateList();
        createList.setName(name);
        createList.setDescription(description);
        createList.setLanguage(language);
        return createList;
    }

    public static AddMovie addMovie(String media_id) {
        AddMovie addMovie = new AddMovie();
        addMovie.setMedia_id(media_id);
        return addMovie;
    }
}
