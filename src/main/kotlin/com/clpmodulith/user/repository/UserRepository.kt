package com.clpmodulith.user.repository

import com.clpmodulith.security.oauth2.OAuth2Provider
import com.clpmodulith.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User, String> {
    @Query("SELECT u FROM User u JOIN u.oauth2Details d WHERE d.provider = :provider AND d.id = :id")
    fun findByOauth2Detail(@Param("provider") provider: OAuth2Provider, @Param("id") id: String): User?
}