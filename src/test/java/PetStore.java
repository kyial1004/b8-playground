import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.SQLOutput;

public class PetStore {
    /*
    1. Have /launch a client(postman)
	2. Have valid URL
	3. Specify desired/supported HTTP method
	4. Define headers
	5. Define query string parameters (if needed)
	6. Execute/click on Send button
Execute/click on Send button
     */
    @Test
    public void getPetTest() throws IOException, URISyntaxException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        //https://petstore.swagger.io/v2/pet/1

        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/1");

        //Specify desired/supported HTTP method
        HttpGet httpGet = new HttpGet(uriBuilder.build());

        //Execute/click on Send button

        HttpResponse response =  client.execute(httpGet);
        int statusCode =  response.getStatusLine().getStatusCode();
        Assert.assertEquals(200, statusCode);


        String responseBody =  EntityUtils.toString(response.getEntity());
        System.out.println(responseBody);
    }
}
