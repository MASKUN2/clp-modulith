package com.clpmodulith.security.model

import com.clpmodulith.security.oauth2.OAuth2Provider
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

data class PrincipalUser(
    val userId: String,
    val authenticatedBy : Set<OAuth2Provider>

) : CustomPrincipal, OAuth2User {

    override fun getAuthenticationProviders(): Set<OAuth2Provider> {
        return authenticatedBy
    }

    override fun getName(): String {
        return userId
    }

    override fun getAttributes(): MutableMap<String, Any> {
        TODO("Not yet implemented")
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PrincipalUser

        return userId == other.userId
    }

    override fun hashCode(): Int {
        return userId.hashCode()
    }

}
