package com.clpmodulith.security.model

import com.clpmodulith.security.oauth2.OAuth2Provider
import java.security.Principal

interface CustomPrincipal  : Principal {

    fun getAuthenticationProviders(): Set<OAuth2Provider>



}