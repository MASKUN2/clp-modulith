package com.clpmodulith.user.model

import com.clpmodulith.coremodel.IdEqualHashCodeEntity
import jakarta.persistence.Entity

@Entity
class User (username: String) : IdEqualHashCodeEntity() {

    var username: String = username
        protected set

}