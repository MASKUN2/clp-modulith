package com.clpmodulith.security

import jakarta.servlet.http.HttpServletRequest

object HttpRequestJwtReader {
    private const val AUTHORIZATION_HEADER = "Authorization"
    private const val JWT_PREFIX = "Bearer"
    private const val JWT_COOKIE_NAME = "jwt_token"

    fun read(request: HttpServletRequest): String? {
        //from cookie
        request.cookies
            ?.firstOrNull { it.name == JWT_COOKIE_NAME }
            ?.let {
                return it.value
            }

        //from Request Header
        val authHeader = request.getHeader(AUTHORIZATION_HEADER)
        authHeader?.let {
            return it.replace(JWT_PREFIX, "").trim()
        }

        return null
    }
}