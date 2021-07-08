package deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;

import java.io.IOException;
import java.net.URISyntaxException;

public class DeserializationWithPojo {

    @Test
    public void getPetTest() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder=new URIBuilder();
        //https://petstore.swagger.io/v2/pet/1
        uriBuilder.setHost("petstore.swagger.io")
                .setScheme("http")
                .setPath("v2/pet/1");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept","application/json");
        HttpResponse response=client.execute(httpGet);
        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
        ObjectMapper objectMapper=new ObjectMapper();
        PetPojo parseResp=objectMapper.readValue(response.getEntity().getContent(), PetPojo.class);
        int id=parseResp.getId();
        String name=parseResp.getName();
        System.out.println("Pet is: "+id);
        System.out.println("Pet name: "+name);
    }
}



