package com.clpmodulith.security.oauth2

import com.clpmodulith.security.OAuth2SubjectIdentifiable
import com.clpmodulith.security.authentication.ApplicationAuthenticationService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

/**
 * 책임과 역할:
 * - OAuth2 기본 인증로직을 수행한 기본 Authentication을 바탕으로 OAuth2Subject 를 추출합니다.
 * - OAuth2Subject 를 ApplicationSubjectLoader 에 전달하고 찾아오는 것을 위임합니다.
 * - 찾은 ApplicationSubject 과 HttpServletRequest 을 ApplicationAuthenticationProvider 에게 전달해서 Authentication 을 가져옵니다.
 * - 가져온 Authentication 를 SecurityContextHolder 에 셋팅 합니다.
 */
@Component
class OAuth2AuthenticationSuccessHandler(
    private val applicationAuthenticationService: ApplicationAuthenticationService
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        require(authentication is OAuth2AuthenticationToken)


        val oauth2Response : Map<String, String> = authentication.principal.attributes.get("response") as Map<String, String>
        val subjectId = OAuth2SubjectIdentifiable(
            authentication.authorizedClientRegistrationId,
            oauth2Response["id"] as String
        )

        applicationAuthenticationService.authenticate(request, response, subjectId)

    }

}
