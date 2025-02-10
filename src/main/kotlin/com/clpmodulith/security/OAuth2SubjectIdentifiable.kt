package com.clpmodulith.security

data class OAuth2SubjectIdentifiable(
    val oAuth2ProviderName: String,
    val oAuth2subjectId: String
) : ApplicationSubjectIdentifiable
