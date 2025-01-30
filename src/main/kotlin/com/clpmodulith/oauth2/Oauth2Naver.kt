package com.clpmodulith.oauth2

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.naver")
data class Oauth2Naver(
    val clientId : String,
    val clientSecret : String
)
