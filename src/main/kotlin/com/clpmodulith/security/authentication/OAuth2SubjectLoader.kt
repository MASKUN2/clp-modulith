package com.clpmodulith.security.authentication

import com.clpmodulith.security.ApplicationSubject
import com.clpmodulith.security.OAuth2SubjectIdentifiable
import com.clpmodulith.user.UserApi
import org.springframework.stereotype.Component

@Component
class OAuth2SubjectLoader(private val userApi: UserApi) {

    fun load(oAuthSubjectId: OAuth2SubjectIdentifiable): ApplicationSubject {
        val userDetails = userApi.findByOAuth2(oAuthSubjectId) ?: userApi.createUser(oAuthSubjectId)
        return ApplicationSubject(userDetails.userId, userDetails.role.name)
    }
}