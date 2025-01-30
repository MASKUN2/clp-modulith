package com.clpmodulith.security

import com.clpmodulith.oauth2.Oauth2Provider

data class PrincipalUser(
    val userId: String,
    val authenticatedBy : Set<Oauth2Provider>

) : ClpPrincipal {

    override fun getAuthenticationProviders(): Set<Oauth2Provider> {
        return authenticatedBy
    }

    override fun getName(): String {
        return userId
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
