package com.clpmodulith.security

import jakarta.transaction.NotSupportedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class ApplicationSubject(
    val id: String,
    val role: String,
    val authorityList: Set<GrantedAuthority> = setOf()

) : Authentication {

    override fun getName(): String {
        return id
    }

    override fun getAuthorities(): Set<GrantedAuthority> =
        authorityList + SimpleGrantedAuthority(role)

    /**
     * returns only null
     */
    override fun getCredentials(): Any? {
        return null
    }

    override fun getDetails(): ApplicationSubject {
        return this
    }

    override fun getPrincipal(): Any {
        return id
    }

    override fun isAuthenticated(): Boolean {
        return true
    }

    @Throws(NotSupportedException::class)
    override fun setAuthenticated(isAuthenticated: Boolean) {
        throw NotSupportedException()
    }
}
