package nextstep.oauth2;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static nextstep.oauth2.GithubLoginRedirectFilter.REDIRECT_URI;

public class GithubRequestClient {

    private static final String GITHUB_TOKEN_URL = "http://localhost:8089/login/oauth/access_token";
    public static final String GITHUB_PROFILE_URL = "http://localhost:8089/user";
//    private static final String GITHUB_TOKEN_URL = "https://github.com/login/oauth/access_token";
//    public static final String GITHUB_PROFILE_URL = "https://api.github.com/user";
    private final RestTemplate restTemplate = new RestTemplate();

    public String requestAccessToken(String code) {
        System.out.println("GithubRequestClient.requestAccessToken");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


        Map<String, String> body = new HashMap<>();
        body.put("client_id", "Ov23liKqs4fJ24nNmjD7");
        body.put("client_secret", "4e2fdc7c10fefc769d691b996f53a4b7140a29c7");
        body.put("code", code);
        body.put("redirect_uri", REDIRECT_URI);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.exchange(GITHUB_TOKEN_URL, HttpMethod.POST, request, Map.class);
        return (String) response.getBody().get("access_token");

    }

    public Map<String, String> requestUserProfile(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(GITHUB_PROFILE_URL, HttpMethod.GET, request, Map.class);
        return response.getBody();
    }


}
