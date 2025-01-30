package com.clpmodulith.security

import com.clpmodulith.oauth2.Oauth2Provider
import java.security.Principal

interface ClpPrincipal  : Principal {

    fun getAuthenticationProviders(): Set<Oauth2Provider>



}