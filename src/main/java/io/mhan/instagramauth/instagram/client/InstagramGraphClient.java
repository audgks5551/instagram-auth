package io.mhan.instagramauth.instagram.client;

import io.mhan.instagramauth.instagram.config.InstagramConfiguration;
import io.mhan.instagramauth.instagram.response.TokenResponse;
import io.mhan.instagramauth.instagram.response.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(name = "instagram-graph", url = "${custom.instagram.graph-uri}", configuration = InstagramConfiguration.class)
public interface InstagramGraphClient {
    @GetMapping(value = "/access_token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    TokenResponse getAccessToken(@RequestParam(value = "grant_type") String grantType,
                                 @RequestParam(value = "client_secret") String clientSecret,
                                 @RequestParam(value = "access_token") String accessToken);

    @GetMapping(value = "/{userId}")
    UserInfoResponse getUserInfo(@PathVariable String userId,
                                 @RequestParam(value = "fields") String fields,
                                 @RequestParam(value = "access_token") String accessToken);
}
