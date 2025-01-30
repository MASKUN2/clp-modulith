package com.clpmodulith.user.service

import com.clpmodulith.user.UserApi
import com.clpmodulith.user.dto.UserInfo
import com.clpmodulith.user.model.User
import com.clpmodulith.user.dto.UserCreateRequest
import com.clpmodulith.user.dto.UserDetailDto
import com.clpmodulith.user.dto.mapper.mapInfo
import com.clpmodulith.user.dto.mapper.mapToUserDetail
import com.clpmodulith.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository) : UserApi {

    override fun createUser(request: UserCreateRequest): UserInfo {
        return mapInfo(userRepository.save(User(name = request.username)))
    }

    override fun getUserDetails(userId: String): UserDetailDto? {
       return userRepository.findByIdOrNull(userId)?.let {
          mapToUserDetail(it)
       }
    }
}