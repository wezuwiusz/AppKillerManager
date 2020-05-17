package com.thelittlefireman.appkillermanager.managers

class NoActionFoundException internal constructor(message: String? = "Intent couldn't find action") : Exception(message)
