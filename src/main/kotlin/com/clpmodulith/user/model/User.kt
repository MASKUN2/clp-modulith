package com.clpmodulith.user.model

import com.clpmodulith.coremodel.IdEqualHashCodeEntity
import com.clpmodulith.security.oauth2.Role
import jakarta.persistence.*

@Entity
class User(
    name: String,
    role: Role = Role.GUEST
    ) : IdEqualHashCodeEntity() {

    @Column(name = "name")
    var name: String = name
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: Role = role
        protected set

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_oauth2_detail", joinColumns = [JoinColumn(name = "user_id")])
    var oauth2Details : MutableSet<Oauth2Detail> = mutableSetOf()




}