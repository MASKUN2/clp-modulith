package com.clpmodulith.user.repository

import com.clpmodulith.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
}