package com.clpmodulith.security.oauth2

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.DefaultOAuth2User

class CustomOauth2User(
    email: String?,
    role: Role,
    authorities: MutableCollection<out GrantedAuthority>?,
    attributes: MutableMap<String, Any>?,
    nameAttributeKey: String?
) : DefaultOAuth2User(authorities, attributes, nameAttributeKey) {

    var email: String? = email
        private set
    var role: Role = role
        private set
}