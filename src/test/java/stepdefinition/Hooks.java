package stepdefinition;

import io.cucumber.java.Before;

import java.io.FileNotFoundException;

public class Hooks {
    @Before("@getmovielist")
    public void beforeScenario() throws FileNotFoundException {
        Test accountSectionTest = new Test();
        if(Test.movieId == null) {
            accountSectionTest.user_sets_the_specification();
            accountSectionTest.user_hits_the_endpoint_for_the_http_get_favorite_movie_method("GETFAVORITEMOVIE");
        }
    }
}
