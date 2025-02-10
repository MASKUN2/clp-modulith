package com.clpmodulith.security.authentication

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class CustomAuthenticationFilter(
    val httpAuthenticationProvider: HttpAuthenticationProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            loadAuthenticationToContext(request)
        } catch (e: Exception) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            return
        }

        filterChain.doFilter(request, response)
    }

    private fun loadAuthenticationToContext(request: HttpServletRequest) {
        val authentication: Authentication? = httpAuthenticationProvider.attemptAuthenticate(request)

        authentication?.let { SecurityContextHolder.getContext().authentication = it }
    }
}