package com.clpmodulith.user.service

import com.clpmodulith.security.OAuth2SubjectIdentifiable
import com.clpmodulith.security.oauth2.OAuth2Provider
import com.clpmodulith.user.UserApi
import com.clpmodulith.user.dto.UserInfo
import com.clpmodulith.user.model.User
import com.clpmodulith.user.dto.UserCreateRequest
import com.clpmodulith.user.dto.UserDetailDto
import com.clpmodulith.user.dto.mapper.mapInfo
import com.clpmodulith.user.dto.mapper.mapToUserDetail
import com.clpmodulith.user.model.Oauth2Detail
import com.clpmodulith.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class UserService (private val userRepository: UserRepository) : UserApi {

    override fun createUser(request: UserCreateRequest): UserInfo {
        return mapInfo(userRepository.save(User(name = request.username)))
    }

    override fun createUser(oAuth2Subject: OAuth2SubjectIdentifiable): UserDetailDto {
        val oauth2Detail = Oauth2Detail(
            provider = OAuth2Provider.fromRegistrationId(oAuth2Subject.oAuth2ProviderName)!!,
            id = oAuth2Subject.oAuth2subjectId,
            authenticatedAt = Instant.now()
        )
        val user = User("unknown")
        user.oauth2Details.add(oauth2Detail)

        userRepository.save(user)
        return mapToUserDetail(user)
    }

    @Transactional(readOnly = true)
    override fun getUserDetails(userId: String): UserDetailDto? {
       return userRepository.findByIdOrNull(userId)?.let {
          mapToUserDetail(it)
       }
    }

    override fun findByOAuth2(oAuth2Subject: OAuth2SubjectIdentifiable): UserDetailDto? {
        val user = userRepository.findByOauth2Detail(OAuth2Provider.fromRegistrationId(oAuth2Subject.oAuth2ProviderName)!!, oAuth2Subject.oAuth2subjectId)
        return if(user != null) {
            mapToUserDetail(user)
        }else{
            null
        }
    }
}