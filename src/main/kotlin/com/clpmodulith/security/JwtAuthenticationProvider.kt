package com.clpmodulith.security

import com.clpmodulith.oauth2.Oauth2Provider
import com.clpmodulith.user.UserApi
import com.clpmodulith.user.dto.UserDetailDto
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service

@Service
class JwtAuthenticationProvider(
    val jwtComponent: JwtComponent,
    val userApi: UserApi
) {

    fun getAuthentication(jwt: String): PreAuthenticationInfo {
        val userId: String = userIdFromJwt(jwt)
        val userDetails: UserDetailDto = userApi.getUserDetails(userId) ?: throw IllegalArgumentException("User not found")
        val oauth2Providers: Set<Oauth2Provider> = userDetails.oauth2Details.map { it.provider }.toSet()
        val principal: ClpPrincipal = PrincipalUser(userId, oauth2Providers)

        return PreAuthenticationInfo( principal = principal,
            mutableSetOf(SimpleGrantedAuthority(userDetails.role.authorityName))
        )
    }


    private fun userIdFromJwt(jwt: String): String {
        val userId: String? = jwtComponent.getClaimValue(jwt, "userId", String::class.java)
        checkNotNull(userId)
        return userId
    }

}