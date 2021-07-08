package utils;

public class PatPayLoadUtils {

    public static String getPetPayLoad(int petId, String petName, String petStatus) {
        String requestBody = "{\n" +
                "  \"id\": " + petId + ", \n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"" + petName + "\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [],\n" +
                "  \"status\": \"" + petStatus + "\"\n" +
                "}";

        return requestBody;
    }

    public static String getSlackMessagePayLoad(String channelId, String msg) {
        String payLoad = "{\n" +
                "    \"channel\":\""+ channelId+"\",\n" +
                "    \"text\":\""+ msg+ "\"\n" +
                "}";
        return  payLoad;
    }
}
