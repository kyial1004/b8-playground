package deserialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Covid19 {

    @Test
    public void getCountriesTest() throws URISyntaxException, IOException {
        // constr a client

        HttpClient client = HttpClientBuilder.create().build();
        // https://corona.lmao.ninja/v2/countries/

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath("v2/countries/")
                .setScheme("https")
                .setHost("corona.lmao.ninja");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        // headers -> Accept
        httpGet.setHeader("Accept", "application/json");

        //execute request
        HttpResponse response = client.execute(httpGet);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        // deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> parsedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<List<Map<String, Object>>>() {
                });

        long maxCases = 0;
        String maxCasesCountry = "";

        System.out.println("Total number of countries " + parsedResponse.size());
        for (int i = 0; i < parsedResponse.size(); i++) {
            Map<String, Object> countryMap = parsedResponse.get(i);
            String countryName = (String) countryMap.get("country");
            long CovidCases = (int) countryMap.get("cases");
            // max = 0, covidCases = 10
            if (CovidCases > maxCases) {
                maxCases = CovidCases;
                maxCasesCountry = (String) countryMap.get("country");
            }
            System.out.println("Country name: " + countryName);
        }
        System.out.println("Max cases: " + maxCases + ", country name: " + maxCasesCountry);
    }

    @Test
    public void covidTest2() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        //https://corona.lmao.ninja/v2/countries/
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("corona.lmao.ninja").setPath("v2/countries");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = httpClient.execute(httpGet);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        // validate if response body is in JSON format
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("json"));

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> countryList = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<List<Map<String, Object>>>() {
                });

        for (int i = 0; i < countryList.size(); i++) {
            countryList.get(i);
            Map<String, Object> countryMap = countryList.get(i);
            Map<String, Object> countryInfoMap = (Map<String, Object>) countryMap.get("countryInfo");
            String countryCode = " ";
                    //countryInfoMap.get("iso3").toString();
            System.out.println("Country code: " + countryCode);
            String countryName = (String) countryMap.get("country");
            try {
                countryCode = countryInfoMap.get("iso3").toString();
            } catch (NullPointerException e) {
            }
            if (countryCode == " ") {
                System.out.println(countryName + ": doesn't have country code");
            } else {
                System.out.println(countryName + " code is: " + countryCode);
            }
        }
    }

            @Test
            public void getRecoveryResult () throws URISyntaxException, IOException {
                HttpClient client = HttpClientBuilder.create().build();
                URIBuilder uriBuilder = new URIBuilder();
                uriBuilder.setScheme("https").setPath("v2/countries").setHost("corona.lmao.ninja");
                HttpGet get = new HttpGet(uriBuilder.build());
                get.setHeader("Access", "application/json");
                HttpResponse response = client.execute(get);
                Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
                ObjectMapper objectMapper = new ObjectMapper();
                List<Map<String, Object>> countriesList = objectMapper.readValue(response.getEntity().getContent(),
                        new TypeReference<List<Map<String, Object>>>() {
                        });
                int maxRecovery = 0;
                int minRecovery = 6000000;
                Map<String, Integer> Map = new TreeMap<>();
                for (int i = 0; i < countriesList.size(); i++) {
                    Map<String, Object> countryMap = countriesList.get(i);
                    int recoveryNum = (Integer) countryMap.get("recovered");
                    Map.put((String) countryMap.get("country"), recoveryNum);
                    minRecovery = Collections.min(Map.values());
                    if (recoveryNum > maxRecovery) {
                        maxRecovery = recoveryNum;
                    }
                }
                System.out.println("Min recovery number is: " + minRecovery);
                System.out.println("Max recovery number is: " + maxRecovery);
                System.out.println(Map);
            }
        }








