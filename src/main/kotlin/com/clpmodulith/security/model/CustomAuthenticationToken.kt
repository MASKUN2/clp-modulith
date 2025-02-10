package com.clpmodulith.security.model

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.WebAuthenticationDetails

class CustomAuthenticationToken(
    private val principal: CustomPrincipal,
    private var isAuthenticated : Boolean = true,
    private val authorities : MutableCollection<out GrantedAuthority>,
    private val webAuthenticationDetails : WebAuthenticationDetails
) : Authentication {

    override fun getName(): String {
        return principal.name
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getCredentials(): Any? {
        return null
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
        this.isAuthenticated = isAuthenticated
    }
}