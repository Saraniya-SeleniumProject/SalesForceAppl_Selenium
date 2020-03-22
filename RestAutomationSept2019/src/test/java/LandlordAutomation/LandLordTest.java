package LandlordAutomation;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

public class LandLordTest {

	@Test(enabled=true)
	public void getLandlord() {
		given()
		.when()
		.get("http://localhost:8080/landlords")
		.then()
		.body("", is(empty()))
		.extract().response().body().prettyPrint();
	}

	@Test(enabled=false)
	public void addLandlord(){
		//LandLord landLord=new LandLord("Andrew123","Math",true);
		String str=given()
				.contentType(ContentType.JSON)
				.body("{"					
						+ "\"firstName\": \"Andrew12356789\","
						+ "\"lastName\": \"Math\""
						+ "\"trusted\": 50,"
						+ "}")
				//.body(landLord)
				.when()
				.post("http://localhost:8080/landlords")
				.then()
				.statusCode(201)
				//.extract().response().jsonPath().getString("id");
				.extract().response().body().prettyPrint();
		JsonPath path=new JsonPath(str);
		String ID=path.getString("id");
		System.out.println(ID); 

		given()
		.pathParam("id",ID)
		.when()
		.get("http://localhost:8080/landlords/{id}")
		.then()
		.extract().response().body().prettyPrint();
	}


	@Test
	public void upadteLandlordID() {
		LandLord landLord=new LandLord("Andrew12345","Math",true);
		String str=given()
				.contentType(ContentType.JSON)
				.body(landLord)
				.when()
				.post("http://localhost:8080/landlords")
				.then()
				.statusCode(201)
				//.extract().response().jsonPath().getString("id");
				.extract().response().body().prettyPrint();
		JsonPath path=new JsonPath(str);
		String ID=path.getString("id");
		System.out.println(ID); 

		LandLord landLord1=new LandLord("Andrew123456","Math",true);
		given()
		.contentType(ContentType.JSON)
		.body(landLord1)
		.pathParam("id",ID)
		.when()
		.put("http://localhost:8080/landlords/{id}")
		.then()
		.statusCode(200)
		.extract().response().body().prettyPrint();
	}
	@Test
	public void deleteLandlordID() {
		LandLord landLord=new LandLord("Andrew1234","Math",true);
		String str=given()
				.contentType(ContentType.JSON)
				.body(landLord)
				.when()
				.post("http://localhost:8080/landlords")
				.then()
				.statusCode(201)
				//.extract().response().jsonPath().getString("id");
				.extract().response().body().prettyPrint();
		JsonPath path=new JsonPath(str);
		String ID=path.getString("id");
		System.out.println(ID); 

		given()
		.pathParam("id",ID)
		.when()
		.delete("http://localhost:8080/landlords/{id}")
		.then()
		.statusCode(200);
	}



}

