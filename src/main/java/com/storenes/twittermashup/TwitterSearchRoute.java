package com.storenes.twittermashup;

import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterSearchRoute extends FatJarRouter {
 
	private static final String CONSUMER_KEY = "57l2QrRYTjqPI9xc30FW6gHSL";
	private static final String CONSUMER_SECRET = "BrualO9bn3lF0ubB1L2rGzKNiBduhDwBlcP0PiOIagrtH3VlNX";
	private static final String ACCESS_TOKEN = "19337223-QiKfjU96ErybcPYNP1UZ4aINoUuidZwrBu34RPu1T";
	private static final String ACCESS_TOKEN_SECRET = "zc3AMqq7yyvx2F82pV7z4Yg1BBHNjyUIghCeXIis3o7Ep";
	
    @Override
    public void configure() throws Exception {
		 rest("/status")
		 .get("/search/{query}").route()
		 .setHeader("CamelTwitterKeywords", header("query"))
		 
		 .to("twitter://search?type=POLLING" + 
				 "&consumerKey=" + CONSUMER_KEY + 
				 "&consumerSecret=" + CONSUMER_SECRET +
				 "&accessToken=" + ACCESS_TOKEN +
				 "&accessTokenSecret=" + ACCESS_TOKEN_SECRET)
		.log("Body: ${body.toString}");
		
		restConfiguration().component("servlet")
				.bindingMode(RestBindingMode.json)
				.dataFormatProperty("prettyPrint", "true")
				.enableCORS(true);
    }
}
