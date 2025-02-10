package com.clpmodulith.user.dto

import com.clpmodulith.Oauth2DetailDto
import com.clpmodulith.security.oauth2.OAuth2Provider
import com.clpmodulith.security.oauth2.Role
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserDetailDto(
    val userId: String,
    val role : Role,
    val oauth2Details : Set<Oauth2DetailDto>
){
    fun authenticatedBy() : Set<OAuth2Provider> {
        return oauth2Details.map { it.provider }.toSet()
    }

    fun getSimpleGrantedAuthorities() = mutableSetOf(SimpleGrantedAuthority(this.role.authorityName))

}
