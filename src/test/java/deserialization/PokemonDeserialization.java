package deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.PokePojo;

import java.io.IOException;
import java.net.URISyntaxException;

public class PokemonDeserialization {

    HttpClient client;
    URIBuilder uriBuilder;
    ObjectMapper objectMapper;
    HttpGet httpGet;

    @Before
    public void setup() throws URISyntaxException {
        client = HttpClientBuilder.create().build();
        //https://pokeapi.co/api/v2/ability
        uriBuilder = new URIBuilder();

        uriBuilder.setScheme("https").setHost("pokeapi.co").setPath("api/v2/ability");

        httpGet = new HttpGet(uriBuilder.build());
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getAbilityTest() throws IOException {

        HttpResponse response = client.execute(httpGet);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        PokePojo parsedResponse = objectMapper.readValue(response.getEntity().getContent(), PokePojo.class);
        int count = parsedResponse.getCount();

        Assert.assertEquals("Failed to get valid ability count", 327, count);


    }
}
