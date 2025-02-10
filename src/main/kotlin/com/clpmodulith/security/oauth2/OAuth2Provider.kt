package com.clpmodulith.security.oauth2

enum class OAuth2Provider (
    val registrationId: String,
    val description: String
){
    NAVER("naver","naver.com");

    companion object {
        fun fromRegistrationId(registrationId: String): OAuth2Provider? {
            return entries.find { it.registrationId == registrationId }
        }
    }
}