package com.anderson.schedulecake.rest

import com.google.gson.annotations.SerializedName

data class LoginResquest (@SerializedName("login")var login: String? = "",@SerializedName("password")var password:String? = "",@SerializedName("role")var role:String? = "") {
//    constructor() : this("", "","")
}
