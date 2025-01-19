package com.clpmodulith.user

import com.clpmodulith.user.dto.UserCreateRequest
import com.clpmodulith.user.dto.UserInfo

interface UserApi {
    fun createUser(request : UserCreateRequest) : UserInfo
}