package com.taehun.noticeboard.oauth.support;

import java.util.Map;

public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if (registrationId.equals("kakao")) {
            return ofKakao(userNameAttributeName, attributes);
        } else {
            return registrationId.equals("naver") ? ofNaver(userNameAttributeName, attributes) : ofGoogle(userNameAttributeName, attributes);
        }
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakao_account = (Map)attributes.get("kakao_account");
        Map<String, Object> profile = (Map)kakao_account.get("profile");
        return new OAuthAttributes(attributes, userNameAttributeName, (String)profile.get("nickname"), (String)kakao_account.get("email"));
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map)attributes.get("response");
        return new OAuthAttributes(attributes, userNameAttributeName, (String)response.get("name"), (String)response.get("email"));
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return new OAuthAttributes(attributes, userNameAttributeName, (String)attributes.get("name"), (String)attributes.get("email"));
    }

    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public String getNameAttributeKey() {
        return this.nameAttributeKey;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public OAuthAttributes(final Map<String, Object> attributes, final String nameAttributeKey, final String name, final String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }
}