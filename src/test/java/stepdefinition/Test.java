package stepdefinition;

import endpoints.EndPoint;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageobjects.AuthorizationRequestTokenPageObject;
import pojos.CreateList;
import propertyutils.PropertyLoader;
import specbuilders.SpecBuilder;
import utils.TestData;

import java.io.FileNotFoundException;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static propertyutils.GetJSONPathValue.getJSONPathValue;

public class Test {
    RequestSpecification requestSpecification;
    Response response;
    static String movieId;
    static String requestToken;
    static String accessToken;
    static String sessionId;

    static String list_id;

    @Given("User sets the specification")
    public void user_sets_the_specification() throws FileNotFoundException {
        requestSpecification = given().spec(SpecBuilder.setRequestSpec());
    }

    @When("User hits the {string} endpoint for the http get account detail method")
    public void user_hits_the_endpoint_for_the_http_get_account_detail_method(String endpoint) {
        response = requestSpecification
                .pathParam("account_id", PropertyLoader.getPropertyLoader().getAccountId())
                .when()
                .get(EndPoint.valueOf(endpoint).getEndpoint());
    }

    @When("User hits the {string} endpoint for the http add favorite movie method")
    public void user_hits_the_endpoint_for_the_http_add_favorite_movie_method(String endpoint) {
        response = requestSpecification
                .pathParam("account_id", PropertyLoader.getPropertyLoader().getAccountId())
                .body(TestData.addFavouriteMovie("movie", movieId, true))
                .when()
                .post(EndPoint.valueOf(endpoint).getEndpoint());
    }

    @When("User hits the {string} endpoint for the http get favorite movie method")
    public void user_hits_the_endpoint_for_the_http_get_favorite_movie_method(String endpoint) {
        response = requestSpecification
                .pathParam("account_id", PropertyLoader.getPropertyLoader().getAccountId())
                .queryParams("language", "en-US", "page", "1", "sort-by", "created_at.desc")
                .when()
                .get(EndPoint.valueOf(endpoint).getEndpoint())
                .then()
                .extract()
                .response();
        movieId = getJSONPathValue(response, "results[1].id");
    }

    @Then("User validates if the status code is equal {int}")
    public void user_validates_if_the_status_code_is_equal(Integer expectedStatusCode) {
        response
                .then()
                .extract()
                .response();
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
    }

    @And("{string} in response body is equal {string}")
    public void value_in_response_body(String actualValue, String expectedValue) {
        Assert.assertEquals(getJSONPathValue(response, actualValue), expectedValue);
    }

    @When("User hits the {string} endpoint for the http get method with movie id as a path param and receives response")
    public void user_hits_the_endpoint_for_the_http_get_ethod(String endpoint) {
        response = requestSpecification
                .pathParam("movie_id", movieId)
                .when()
                .get(EndPoint.valueOf(endpoint).getEndpoint());
    }

    @Given("User sets the specification and create Request Token with {string} endpoint")
    public void userSetsTheSpecificationAndCreateRequestToken(String endpoint) throws FileNotFoundException {
        response =
                given()
                        .spec(SpecBuilder.setRequestSpec())
                        .body(TestData.addRedirectTo("https://www.themoviedb.org/"))
                        .when()
                        .post(EndPoint.valueOf(endpoint).getEndpoint())
                        .then()
                        .extract()
                        .response();
        requestToken = getJSONPathValue(response, "request_token");
    }

    @When("User authorize the request token")
    public void userAuthorizeTheRequestToken() throws FileNotFoundException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.themoviedb.org/auth/access?request_token=" + requestToken);
        AuthorizationRequestTokenPageObject authorizationRequestTokenPageObject = new AuthorizationRequestTokenPageObject(driver);
        authorizationRequestTokenPageObject.authorizationRequestToken("mk90", "romekk11");
        driver.quit();
    }

    @Then("User gets the Access Token with {string} endpoint")
    public void userGetsTheAccessToken(String endpoint) throws FileNotFoundException {
        response =
                given()
                        .spec(SpecBuilder.setRequestSpec())
                        .body(TestData.addRequestToken(requestToken))
                        .when()
                        .post(EndPoint.valueOf(endpoint).getEndpoint())
                        .then()
                        .extract()
                        .response();
        accessToken = getJSONPathValue(response, "access_token");
    }

    @Then("User create session with hitting the {string} endpoint for the http create session method")
    public void user_hits_the_endpoint_for_the_http_create_session_method(String endpoint) throws FileNotFoundException {
        response =
                given()
                        .spec(SpecBuilder.setRequestSpec())
                        .body(TestData.addAccessToken(accessToken))
                        .when()
                        .post(EndPoint.valueOf(endpoint).getEndpoint())
                        .then()
                        .extract()
                        .response();
        sessionId = getJSONPathValue(response, "session_id");
    }

    @Then("User create the list with the {string} endpoint for the http create list method")
    public void user_hits_the_endpoint_for_the_http_create_list_method(String endpoint) throws FileNotFoundException {
        response =
                requestSpecification
                        .when()
                        .post(EndPoint.valueOf(endpoint).getEndpoint())
                        .then()
                        .extract()
                        .response();
        list_id = getJSONPathValue(response, "list_id");
    }

    @When("User hits the {string} endpoint for the http add movie method")
    public void user_hits_the_endpoint_for_the_http_add_movie_method(String endpoint) {
        response = requestSpecification
                .pathParam("list_id", list_id)
                .queryParam(sessionId)
                .body(TestData.addMovie(movieId))
                .when()
                .post(EndPoint.valueOf(endpoint).getEndpoint())
                .then()
                .extract()
                .response();
    }

    @When("User hits the {string} endpoint for the http get details of the list method")
    public void user_hits_the_endpoint_for_the_http_get_details_of_the_list_method(String endpoint) throws FileNotFoundException {
        response = requestSpecification
                .pathParam("list_id", list_id)
                .queryParams("language", "en-US", "page", 1)
                .when()
                .get(EndPoint.valueOf(endpoint).getEndpoint())
                .then()
                .extract()
                .response();
    }

    @When("User hits the {string} endpoint for the delete method")
    public void user_hits_the_endpoint_for_the_delete_method(String endpoint) throws FileNotFoundException {
        response = requestSpecification
                .pathParam("list_id", list_id)
                .queryParam("session_id", sessionId)
                .when()
                .delete(EndPoint.valueOf(endpoint).getEndpoint())
                .then()
                .extract()
                .response();
    }

    @DataTableType
    public CreateList entryListData(Map<String, String> entryData) {
        return new CreateList(entryData.get("name"), entryData.get("description"), entryData.get("language"));
    }

    @And("User sends the payload for the http post method in bellow format")
    public void userSendsThePayloadForTheHttpPostMethodInBellowFormat(CreateList createList) throws FileNotFoundException {
        requestSpecification = given()
                .spec(SpecBuilder.setRequestSpec())
                .queryParam("session_id", sessionId)
                .body(createList);
    }

    @When("User hits the {string} endpoint for the http check item status method")
    public void userHitsTheCHECKITEMSTATUSEndpointForTheHttpCheckItemStatusMethod(String endpoint) {
        response = requestSpecification
                .when()
                .pathParam("list_id", list_id)
                .get(EndPoint.valueOf(endpoint).getEndpoint())
                .then()
                .extract()
                .response();
    }
}
