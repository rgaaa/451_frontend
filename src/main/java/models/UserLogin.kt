package models

import com.google.gson.annotations.SerializedName

data class UserLogin (
    @SerializedName("username")
    var username: String,
    @SerializedName("password")
    var password: String
)
