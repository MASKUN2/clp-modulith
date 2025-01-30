package com.clpmodulith.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.WebAuthenticationDetails

class ClpAuthenticationToken(
    private val principal: ClpPrincipal,
    private val credential: String,
    private val isAuthenticated : Boolean = true,
    private val authorities : MutableCollection<out GrantedAuthority>,
    private val webAuthenticationDetails : WebAuthenticationDetails
) : Authentication {

    override fun getName(): String {
        return principal.name
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getCredentials(): Any {
        return credential
    }

    override fun getDetails(): Any {
        return webAuthenticationDetails
    }

    override fun getPrincipal(): Any {
        return principal
    }

    override fun isAuthenticated(): Boolean {
        return isAuthenticated
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        if (isAuthenticated) {
            throw IllegalArgumentException("Cannot manually set authentication status")
        }
    }
}