package io.mhan.instagramauth.instagram.web;

import io.mhan.instagramauth.instagram.properties.InstagramProperties;
import io.mhan.instagramauth.instagram.response.TokenResponse;
import io.mhan.instagramauth.instagram.response.UserInfoResponse;
import io.mhan.instagramauth.instagram.session.InstagramSession;
import io.mhan.instagramauth.instagram.session.RequestType;
import io.mhan.instagramauth.instagram.usecase.InstagramManagementUseCase;
import io.mhan.instagramauth.instagram.utils.StateGenerator;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/instagram")
@RequiredArgsConstructor
public class InstagramController {
    private final InstagramProperties instagramProperties;
    private final InstagramManagementUseCase instagramManagementUseCase;

    @GetMapping("/certify")
    public String certify(
//            @RequestParam(value = "redirect_uri") String redirectUri,
//            @RequestParam(value = "request_type") RequestType type,
            HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("instagram") == null) {
            session.setAttribute("instagram", InstagramSession.builder().build());
        }

        InstagramSession instagramSession = (InstagramSession) session.getAttribute("instagram");
//        instagramSession.setRequestType(type);
//        instagramSession.setRedirectUri(redirectUri);
        instagramSession.setState(StateGenerator.generateState());

        redirectAttributes.addAttribute("response_type", "code");
        redirectAttributes.addAttribute("client_id", instagramProperties.getClientId());
        redirectAttributes.addAttribute("scope", instagramProperties.getScope());
        redirectAttributes.addAttribute("state", instagramSession.getState());
        redirectAttributes.addAttribute("redirect_uri", instagramProperties.getRedirectUri());
        return "redirect:" + instagramProperties.getAuthorizationUri();
    }

    @GetMapping("/callback")
    @ResponseBody
    public String callback(HttpSession session,
                           @RequestParam String code,
                           @RequestParam String state) {
        InstagramSession instagramSession = (InstagramSession) session.getAttribute("instagram");

        if (!Objects.equals(instagramSession.getState(), state)) {
            return "redirect:/";
        }

        TokenResponse tokenResponse = instagramManagementUseCase.getAccessToken(code);

        UserInfoResponse userInfo = instagramManagementUseCase.getUserInfo(tokenResponse.getUserId(), tokenResponse.getAccessToken());

        return userInfo.toString();
    }
}
