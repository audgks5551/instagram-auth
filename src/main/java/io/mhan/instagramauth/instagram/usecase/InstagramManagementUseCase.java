package io.mhan.instagramauth.instagram.usecase;

import io.mhan.instagramauth.instagram.response.TokenResponse;
import io.mhan.instagramauth.instagram.response.UserInfoResponse;

public interface InstagramManagementUseCase {
    TokenResponse getAccessToken(String code);
    UserInfoResponse getUserInfo(String userId, String accessToken);
}
