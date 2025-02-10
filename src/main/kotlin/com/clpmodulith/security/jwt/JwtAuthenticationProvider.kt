package com.clpmodulith.security.jwt

import com.clpmodulith.security.oauth2.OAuth2Provider
import com.clpmodulith.security.model.PrincipalUser
import com.clpmodulith.security.exception.AuthUserNotFoundException
import com.clpmodulith.security.authentication.HttpAuthenticationProvider
import com.clpmodulith.security.model.CustomAuthenticationToken
import com.clpmodulith.user.UserApi
import com.clpmodulith.user.dto.UserDetailDto
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Service

@Service
class JwtAuthenticationProvider(
    val jwtComponent: JwtComponent,
    val userApi: UserApi
) : HttpAuthenticationProvider {

    override fun attemptAuthenticate(request: HttpServletRequest): Authentication? {
        //jwt가 없는 경우 null을 리턴
        val jwt: String = HttpJwtProcessor.read(request) ?: return null

        //유저를 찾을 수 없는 경우 AuthenticationException 을 발생시키고 AuthenticationEntryPoint 에게 예외처리를 위임
        val userId: String = jwtComponent.getSubject(jwt)
        val userDetails: UserDetailDto = userApi.getUserDetails(userId)
            ?: throw AuthUserNotFoundException("User not found userId=$userId")

        val authentication: Authentication = CustomAuthenticationToken(
            principal = PrincipalUser(userId, userDetails.getProviders()),
            authorities = userDetails.getSimpleGrantedAuthorities(),
            webAuthenticationDetails = WebAuthenticationDetails(request),
            isAuthenticated = true
        )
        return authentication
    }

    private fun UserDetailDto.getProviders(): Set<OAuth2Provider> = this.oauth2Details.map { it.provider }.toSet()

}