package com.clpmodulith.security

import org.springframework.security.core.GrantedAuthority

data class PreAuthenticationInfo(
    val principal: ClpPrincipal,
    val authorities : MutableCollection<out GrantedAuthority>
)