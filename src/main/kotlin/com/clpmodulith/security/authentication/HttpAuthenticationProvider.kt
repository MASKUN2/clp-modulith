package com.clpmodulith.security.authentication

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.Authentication


interface HttpAuthenticationProvider {
    fun attemptAuthenticate(request: HttpServletRequest): Authentication?
}