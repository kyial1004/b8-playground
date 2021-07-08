package deserialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class deserializationIntro {
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
    public void test1() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();

        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/1");
        //or
        uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet/1");
        HttpGet get = new HttpGet(uriBuilder.build());

        HttpResponse response = httpClient.execute(get);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String , Object> deserializeResponse =  objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
        });

        System.out.println(deserializeResponse.get("id"));
        System.out.println(deserializeResponse.get("name"));



    }
}
