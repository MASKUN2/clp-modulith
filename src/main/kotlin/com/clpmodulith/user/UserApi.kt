package com.clpmodulith.user

import com.clpmodulith.security.OAuth2SubjectIdentifiable
import com.clpmodulith.user.dto.UserCreateRequest
import com.clpmodulith.user.dto.UserDetailDto
import com.clpmodulith.user.dto.UserInfo

interface UserApi {
    fun createUser(request: UserCreateRequest): UserInfo
    fun createUser(oAuth2Subject: OAuth2SubjectIdentifiable): UserDetailDto


    fun getUserDetails(userId: String): UserDetailDto?

    fun findByOAuth2(oAuth2Subject: OAuth2SubjectIdentifiable): UserDetailDto?
}