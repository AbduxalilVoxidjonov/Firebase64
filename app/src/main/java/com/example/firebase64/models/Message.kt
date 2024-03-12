package com.example.firebase64.models

import java.util.Date

class Message {
    var text: String? = null
    var fromUserUid: String? = null
    var toUserUid: String? = null
    var date: String? = null
    var message: HashMap<String,Any>? = null


    constructor()
    constructor(
        text: String?,
        fromUserUid: String?,
        toUserUid: String?,
        date: String?,
    ) {
        this.text = text
        this.fromUserUid = fromUserUid
        this.toUserUid = toUserUid
        this.date = date
    }
}