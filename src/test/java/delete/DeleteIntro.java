package delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.PatPayLoadUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public class DeleteIntro {

    HttpClient client;

    ObjectMapper objectMapper;
    HttpPost post;
    HttpResponse response;
    HttpDelete delete;
    URIBuilder uriBuilder;

    @Before
    public void setup() throws URISyntaxException, IOException {
        client = HttpClientBuilder.create().build();
        uriBuilder = new URIBuilder();
        //https://petstore.swagger.io/v2/pet
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet");

        post = new HttpPost(uriBuilder.build());
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");

        HttpEntity entity = new StringEntity(PatPayLoadUtils.getPetPayLoad(1010, "goggie", "to be deleted"));
        post.setEntity(entity);
        response = client.execute(post);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    public void deletePetTest() throws URISyntaxException, IOException {
        uriBuilder.setPath("v2/pet/1010");
        delete = new HttpDelete(uriBuilder.build());

        delete.setHeader("Accept", "application/json");

        response = client.execute(delete);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        response = client.execute(get);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

}





