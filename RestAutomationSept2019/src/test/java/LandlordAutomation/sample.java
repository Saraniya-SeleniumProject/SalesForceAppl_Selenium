package LandlordAutomation;


import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;


public class sample {
	
@Test	
public void sampletest ()
{
given()
.pathParam("id", 1)
.when()
.get("https://jsonplaceholder.typicode.com/posts/{id}")
 .then()
.statusCode(200)
.extract().response().prettyPrint();
}
}

