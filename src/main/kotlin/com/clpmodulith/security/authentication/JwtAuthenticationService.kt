package com.clpmodulith.security.authentication

import com.clpmodulith.security.ApplicationSubjectIdentifiable
import com.clpmodulith.security.OAuth2SubjectIdentifiable
import com.clpmodulith.security.jwt.HttpJwtProcessor
import com.clpmodulith.security.jwt.JwtComponent
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

/**
 * 여러 컴포넌트를 사용하여 인증서비스를 오케스트레이션합니다.
 */
@Service
class JwtAuthenticationService(
    private val jwtComponent: JwtComponent,
    private val oAuth2SubjectLoader: OAuth2SubjectLoader
) : ApplicationAuthenticationService {


    override fun authenticate(
        request: HttpServletRequest,
        response: HttpServletResponse,
        identifiable: ApplicationSubjectIdentifiable // 본 앱에서 지원하는 사용자 식별자
    ) {

        //Authentication
        val applicationSubject = when (identifiable) {
            is OAuth2SubjectIdentifiable -> oAuth2SubjectLoader.load(identifiable) //OAuth2
        }
        SecurityContextHolder.getContext().authentication = applicationSubject

        //jwt
        val jwt = jwtComponent.makeJwt(subject = applicationSubject.id)
        HttpJwtProcessor.writeCookie(response, jwt)
    }


}