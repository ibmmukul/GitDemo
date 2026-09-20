package oAuton2;
import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
public class oAuthTest {

	public static void main(String[] args) {
		
		String url ="https://accounts.google.com/signin/oauth/id?authuser=0&part=AJi8hANfXdPy_uO_O7Mcc74fQO60w2tpbRFUUSuJ4cUuKS75M1bILLArcqfB498A5BBPQkpEr-qNuCmoDh_4NviYvRBQC4mxbT6e7Plwh5zcEDQn4NbwEpPH5zbS38yuYZZHk4kZSpOIBbX8t5w5Vl4q8eIkEBYsRF4Y0NEUSl8dFQVRL-01n4ygTNyKVh6sm8UvWh09MmsYTxJE5sGINizgGlTOVTDW37S6gb6jBs27xJFFZUe3EwuZRiiQAJ3fq8Uy1N72WF8vQgcDIDvclnk4LSFUeOK8EYNRgrxNArs5ntwKGdE3AmQtsixzkyUsk2l_CWkaj5sv-Kn3ot1vHFrLccH27DWdyH6wwPXCmzJ4-DHdD17SVt_Sz2CiXva3jUqLOnnyqmHmrgndH2R8Go1B2-5S0S2YqkTeEJUTZEhSUmB96g6xxKiH6aaOUt4HcOtgnvU0hl63pQpvc4JHzfD_Jxag3yXtHSwE_jY0EMCJoJcz_GGEwmMHvEaZceqVyiLMkMa9lsXq4Bs7R-Vih8uxpw7U5gJeaACjmdgGETNIhLFoK-EbKD2FCyNhuanwCAJN5sNCIx1qF9rQgmNsD-xWkR-Yk_DvHzt5-Lhlt2fuPTijygoUEMROH5et_HI9CnBfp9OuYlxMv4LNFDl0RJYQsaD_sTkLNDWCa61dH5mO6z2bVgqZ8HL3L8iT3I0tEp0DD06Asxy5h7cj4GCsF-3dI8ESVvHGpwtuh_EqR1E2apnWD6-N-BDpEzIq6TTAoGJ2NHrylexy4UQRJZY_9haG5zYE4xDWDFdOCTLvHFsb04z8PNigEmq24BJHAO1CnG6yOqR2xmVh6bLTsTGXVmaSq8DdKZuobg&flowName=GeneralOAuthFlow&as=S858233098%3A1789556053149006&client_id=990572338172-iibth2em4l86htv30eg1v44jia37fuo5.apps.googleusercontent.com&rapt=AEjHL4MvLQYAMNNPWWFs6E0qspk4W-8Ou4-LTf4kLqvGmJymzjx6ALn6jYx2Yiq1pbIzVRXhdnKRuVOCbP_RZM0hksbLKrA_6vDV97L-EARH_Y4KCQPwcEY#";



		String partialcode=url.split("code=")[1];

		String code=partialcode.split("&scope")[0];


		System.out.println(code);




		String response =

		                given() 

		                .urlEncodingEnabled(false)

		                       .queryParams("code",code)

		               

		                   .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

		                        .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

		                        .queryParams("grant_type", "authorization_code")

		                        .queryParams("state", "verifyfjdss")

		                        .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

		                     // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")

		                       

		                        .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")

		                        .when().log().all()

		                        .post("https://www.googleapis.com/oauth2/v4/token").asString();

		// System.out.println(response);

		JsonPath jsonPath = new JsonPath(response);

		    String accessToken = jsonPath.getString("access_token");

		    System.out.println(accessToken);

		String r2=    given().contentType("application/json").

		queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)

		.when()

		           .get("https://rahulshettyacademy.com/getCourse.php")

		.asString();

		System.out.println(r2);





		}

}
