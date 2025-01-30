package com.clpmodulith.user.dto.mapper

import com.clpmodulith.Oauth2DetailDto
import com.clpmodulith.user.dto.UserDetailDto
import com.clpmodulith.user.model.User
import com.clpmodulith.user.dto.UserInfo
import com.clpmodulith.user.model.Oauth2Detail

fun mapInfo(user: User): UserInfo = UserInfo(id = user.id!!, username = user.name)

fun mapToUserDetail(u: User): UserDetailDto =
    UserDetailDto(
        userId = u.id!!,
        role = u.role,
        oauth2Details = u.oauth2Details.map { mapToOauthDetail(it) }.toSet()
    )

fun mapToOauthDetail(od: Oauth2Detail): Oauth2DetailDto =
    Oauth2DetailDto(
        id = od.id,
        provider = od.provider,
        authenticatedAt = od.authenticatedAt,
    )
