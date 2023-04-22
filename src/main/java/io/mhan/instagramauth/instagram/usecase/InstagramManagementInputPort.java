package io.mhan.instagramauth.instagram.usecase;

import io.mhan.instagramauth.instagram.response.TokenResponse;
import io.mhan.instagramauth.instagram.response.UserInfoResponse;
import io.mhan.instagramauth.instagram.service.InstagramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstagramManagementInputPort implements InstagramManagementUseCase {
    private final InstagramService instagramService;

    @Override
    public TokenResponse getAccessToken(String code) {
        TokenResponse tokenResponse = instagramService.getAccessToken(code);

        return tokenResponse;
    }

    @Override
    public UserInfoResponse getUserInfo(String userId, String accessToken) {
        UserInfoResponse userInfo = instagramService.getUserInfo(userId, accessToken);

        return userInfo;
    }
}
