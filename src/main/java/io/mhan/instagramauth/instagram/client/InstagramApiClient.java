package io.mhan.instagramauth.instagram.client;

import io.mhan.instagramauth.instagram.config.InstagramConfiguration;
import io.mhan.instagramauth.instagram.response.ShortTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(name = "instagram-api", url = "${custom.instagram.api-uri}", configuration = InstagramConfiguration.class)
public interface InstagramApiClient {
    @PostMapping(value = "/oauth/access_token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    ShortTokenResponse getAccessToken(@RequestBody String request);
}
