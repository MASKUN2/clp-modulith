package com.clpmodulith.security.authentication

import com.clpmodulith.security.ApplicationSubjectIdentifiable
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse


interface ApplicationAuthenticationService {

    fun authenticate(
        request: HttpServletRequest,
        response: HttpServletResponse,
        identifiable: ApplicationSubjectIdentifiable
    )
}