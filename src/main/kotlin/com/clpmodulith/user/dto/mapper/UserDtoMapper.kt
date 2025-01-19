package com.clpmodulith.user.dto.mapper

import com.clpmodulith.user.model.User
import com.clpmodulith.user.dto.UserInfo

fun mapInfo (user: User): UserInfo  = UserInfo(id = user.id!!, username = user.username)
