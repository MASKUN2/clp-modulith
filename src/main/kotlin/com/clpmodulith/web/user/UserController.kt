package com.clpmodulith.web.user

import com.clpmodulith.user.UserApi
import com.clpmodulith.user.dto.UserCreateRequest
import com.clpmodulith.user.dto.UserInfo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userApi: UserApi) {

    @PostMapping("/v1.0/users")
    fun createUser(@RequestBody request: UserCreateRequest): ResponseEntity<UserInfo> {
        return ResponseEntity.ok(userApi.createUser(request))
    }
}