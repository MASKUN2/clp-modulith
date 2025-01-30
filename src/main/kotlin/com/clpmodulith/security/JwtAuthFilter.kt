package com.clpmodulith.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    val jwtAuthProvider: JwtAuthenticationProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt: String? = HttpRequestJwtReader.read(request)

        jwt?.let { loadAuthenticationToContext(jwt, request) }

        filterChain.doFilter(request, response)
    }

    private fun loadAuthenticationToContext(jwt: String, request: HttpServletRequest) {
        val preAuth: PreAuthenticationInfo = jwtAuthProvider.getAuthentication(jwt)

        val authentication: Authentication = ClpAuthenticationToken(
            principal = preAuth.principal,
            credential = jwt,
            authorities = preAuth.authorities,
            webAuthenticationDetails = WebAuthenticationDetails(request)
        )
        SecurityContextHolder.getContext().authentication = authentication
    }
}