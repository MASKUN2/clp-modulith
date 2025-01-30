package com.clpmodulith.user.dto

import com.clpmodulith.Oauth2DetailDto
import com.clpmodulith.oauth2.Role

data class UserDetailDto(
    val userId: String,
    val role : Role,
    val oauth2Details : Set<Oauth2DetailDto>
)
