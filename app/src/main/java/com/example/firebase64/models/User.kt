package com.example.firebase64.models

import java.io.Serializable

class User : Serializable {

    var displayName: String = ""
    var uid: String = ""
    var email: String = ""
    var photoUrl: String = ""

    constructor(displayName: String, uid: String, email: String, photoUrl: String) {
        this.displayName = displayName
        this.uid = uid
        this.email = email
        this.photoUrl = photoUrl
    }

    constructor()

    override fun toString(): String {
        return "User(displayName='$displayName', uid='$uid', email='$email', photoUrl='$photoUrl')"
    }
}