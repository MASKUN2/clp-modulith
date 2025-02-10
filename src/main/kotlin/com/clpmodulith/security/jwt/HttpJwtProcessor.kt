package com.clpmodulith.security.jwt

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

object HttpJwtProcessor {
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

    fun writeCookie(response: HttpServletResponse, jwt: String) {
        response.addCookie(
            Cookie(JWT_COOKIE_NAME, jwt).apply {
                isHttpOnly = true // JS에서 접근 불가능 (보안 강화)
                //secure = true // HTTPS에서만 전송
                path = "/" // 모든 경로에서 쿠키 사용 가능
                maxAge = 60 * 60 * 24 //1 day
            }
        )
    }
}