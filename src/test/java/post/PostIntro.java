package post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;
import utils.PatPayLoadUtils;

import java.io.IOException;

import java.net.URISyntaxException;

public class PostIntro {



    @Test
    public void createPetTest() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        //https://petstore.swagger.io/v2/pet
        uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet");
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        // add requesr body


        HttpEntity httpEntity = new StringEntity(PatPayLoadUtils.getPetPayLoad(6679, "David", "created from java code"));
        httpPost.setEntity(httpEntity);

        HttpResponse response = httpClient.execute(httpPost);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        PetPojo petPojo=objectMapper.readValue(response.getEntity().getContent(),PetPojo.class);

        Assert.assertEquals(6679, petPojo.getId());
        Assert.assertEquals("David", petPojo.getName());
        Assert.assertEquals("created from java code", petPojo.getStatus());

        uriBuilder.setPath("v2/pet/6679");
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");

        response = httpClient.execute(httpGet);

        PetPojo parsedResp = objectMapper.readValue(response.getEntity().getContent(), PetPojo.class);
        Assert.assertEquals(6679, petPojo.getId());
        Assert.assertEquals("David", parsedResp.getName());


    }
}
